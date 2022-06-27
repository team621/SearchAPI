/*
 * Copyright (c) GS Retail.
 * All rights reserved.
 * 모든 권한은 GS리테일에 있으며,
 * GS리테일의 허락없이 소스 및 이진형식을 재배포, 사용하는 행위를 금지합니다.
 */

package com.gsretail.gst.b2e.search.search.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import com.gsretail.gst.b2e.search.search.common.*;
import com.gsretail.gst.b2e.search.search.model.Search;
import com.gsretail.gst.b2e.search.search.service.SearchService;
import static com.gsretail.gst.b2e.search.search.common.WNDefine.*;

/**
 * com.gsretail.gst.b2e.search.search.service
 * SearchServiceImpl
 *
 * @author : WISENUT
 * @date : 2022-06-22
 * @tags : Total Search Service Implements
 */
@Service
public class SearchServiceImpl implements SearchService {
    /* 기존 검색어 (오타 검색) */
    String typoQuery = "";
    /* 오타검색을 위한 전체 검색 결과 카운트 */
    int allTotalCount = 0;
    /* 디버깅 보기 설정 */
    boolean isDebug = false;
    /* 검색엔진 설정 객체 생성 */
    WNUtils wnUtils = new WNUtils();
    WNCollection wncol = new WNCollection();

    /**
     * 통합검색
     *
     * @param search     검색 객체
     * @return
     */
    public JSONObject getTotalSearch(Search search) {
        /* 정타 추천으로도 검색 결과가 없을 경우를 위해 전체 검색 결과 수 초기화 */
        allTotalCount = 0;
        /* 오타 후 추천 검색어 화면 출력 여부 체크 */
        boolean useSuggestedQuery = true;

        String collection = search.getCollection();
        String[] searchFields = null;
        String[] collections = null;

        /* 통합검색 or 개별, 다중 검색 구분 */
        if (collection.equals("ALL")) collections = wncol.COLLECTIONS;
        else collections = collection.split(",");

        /* 검색 엔진 객체 생성 */
        WNSearch wnsearch = new WNSearch(isDebug, false, collections, searchFields);

        /* 컬렉션 설정 */
        setCollectionInfo(wnsearch, collections, search);

        /* 검색 수행 */
        wnsearch.search(search.getQuery(), false, CONNECTION_CLOSE, useSuggestedQuery , true);

        /* 검색결과 생성 */
        Map<String , Object> totalSearchResultMap = getSearchResult(wnsearch, collections, search);

        /* 카테고리 리스트 생성 후 결과값 저장 */
        totalSearchResultMap.put("categoryList" , getCategoryList(wnsearch, collections));

        JSONObject searchResult = new JSONObject();
        searchResult.put("SearchQueryResult", totalSearchResultMap);

        //오타에 대한 정타 추천 검색어
        String typoSearch = search.getTypoSearch();
        String suggestedQuery = wnsearch.suggestedQuery;

        //오타검색 (전체 검색 결과가 없고 오타 수정 단어가 있을 경우, 오타검색일경우)
        if(allTotalCount <= 0 && !suggestedQuery.equals("") && typoSearch.equals("N")) {
            //오타 검색어(기존 검색어) 저장
            typoQuery = search.getQuery();

            //검색어 (오타 → 정타 추천) 수정
            search.setQuery(suggestedQuery);

            //오타로 검색된 기존 결과 Temp 저장
            JSONObject searchResultJsonTemp = getTotalSearch(search);

            //오타 추천 검색 결과가 없을경우 기존 검색결과 사용
            if(allTotalCount > 0)  {
                searchResult = searchResultJsonTemp;
            }
        }
        //오타 검색어 초기화
        typoQuery = "";

        // 디버그 메시지 출력
        String debugMsg = wnsearch.printDebug() != null ? wnsearch.printDebug().trim() : "";
        if (isDebug) System.out.println(debugMsg.replace("<br>", "\n"));
        return searchResult;
    }

    /**
     * 컬렉션 정보 설정
     *
     * @param wnsearch
     * @param collections
     * @param search
     */
    private void setCollectionInfo(WNSearch wnsearch, String[] collections, Search search) {
        for (int i = 0; i < collections.length; i++) {
            wnsearch.setCollectionInfoValue(collections[i], PAGE_INFO, search.getStartCount() + "," + search.getListCount());

            /* 검색어가 없으면 DATE_RANGE 로 전체 데이터 출력 */
            if (!"".equals(search.getQuery())) {
                wnsearch.setCollectionInfoValue(collections[i], SORT_FIELD, search.getSort() + ",exposureSeq/DESC");
            } else {
                wnsearch.setCollectionInfoValue(collections[i], DATE_RANGE, search.getStartDate().replaceAll("[.]", "/") + ",2030/01/01,-");
                wnsearch.setCollectionInfoValue(collections[i], SORT_FIELD, search.getSort() + ",exposureSeq/DESC");
            }

            /* searchField 값이 있으면 설정, 없으면 기본검색필드 */
            String searchField = search.getSearchField();
            if (!searchField.equals("") && !searchField.equals("ALL")) wnsearch.setCollectionInfoValue(collections[i], SEARCH_FIELD, search.getSearchField());

            /* exquery 생성 및 설정 */
            String exquery = wnUtils.setExquery(search);
            if (!"".equals(exquery)) wnsearch.setCollectionInfoValue(collections[i], EXQUERY_FIELD, exquery);

            /* filterquery 설정 */
            String filterQuery = wnUtils.setFilterQuery(search);
            wnsearch.setCollectionInfoValue(collections[i], FILTER_OPERATION, filterQuery);

            /* categoryquery 설정 */
            String categoryQuery = "";
            String[] categories = search.getArrays(search.getCategoryId());
            for (String category : categories) categoryQuery += "categoryId|"+category+",";
            if (!"".equals(search.getCategoryId())) wnsearch.setCollectionInfoValue(collections[i], CATEGORY_QUERY, categoryQuery);

            /* 선택된 컬렉션이 WNCollection 몇번째 인지 index값 */
            int collectionIndex = wnsearch.getCollIdx(collections[i]);
            /* 통합검색시 supermarketItemCode(상품 고유 번호) 이용하여 그룹화 */
            if (!wncol.COLLECTION_INFO[collectionIndex][GROUP_BY].equals("")) {
                wnsearch.setCollectionInfoValue(collections[i], GROUP_BY, wncol.COLLECTION_INFO[collectionIndex][GROUP_BY]);
                wnsearch.setCollectionInfoValue(collections[i], GROUP_SORT_FIELD, search.getSort() + ",exposureSeq/DESC");
            }
        }
    }

    /**
     * 검색결과 생성
     *
     * @param wnsearch
     * @param collections
     * @param search
     * @return 검색 결과값
     */
    private Map<String , Object> getSearchResult(WNSearch wnsearch, String[] collections, Search search) {
        Map<String , Object> searchResultMap = new HashMap<>();
        JSONArray collectionJsonArray = new JSONArray();

        searchResultMap.put("query", search.getQuery());
        searchResultMap.put("Version", "5.3.0");
        searchResultMap.put("typoQuery", typoQuery);
        searchResultMap.put("selectStoreCode", search.getStoreCode());

        /* 컬렉션별 검색 결과 생성 */
        for (int i = 0; i < collections.length; i++) {
            JSONObject documentset = new JSONObject();
            int resultCount = 0;
            int totalCount = 0;

            /* 컬렉션 별 찜 목록 호출 */
            String itemCode = "";
            HashMap<String, String> expectedItemMap = new HashMap<>();
            if(search.getToken() != null) expectedItemMap = getExpectedItems(collections[i], search);

            /* 선택된 컬렉션이 WNCollection 몇번째 인지 index값 */
            int collectionIndex = wnsearch.getCollIdx(collections[i]);
            /* supermarketItemCode로 그룹화 하는 컬렉션 선별하여 검색 결과 count 생성 */
            if (!wncol.COLLECTION_INFO[collectionIndex][GROUP_BY].equals("")) {
                resultCount = wnsearch.getResultGroupCount(collections[i]) < 0 ? 0 : wnsearch.getResultGroupCount(collections[i]);
                totalCount = wnsearch.getResultTotalGroupCount(collections[i]) < 0 ? 0 : wnsearch.getResultTotalGroupCount(collections[i]);
            } else {
                resultCount = wnsearch.getResultCount(collections[i]) < 0 ? 0 : wnsearch.getResultCount(collections[i]);
                totalCount = wnsearch.getResultTotalCount(collections[i]) < 0 ? 0 : wnsearch.getResultTotalCount(collections[i]);
            }

            /* 오타 검색 결과값 확인용 카운트*/
            allTotalCount += totalCount;

            JSONObject countJsonObject = new JSONObject();
            countJsonObject.put("resultCount", resultCount);
            countJsonObject.put("totalCount", totalCount);

            /* 선택된 컬렉션 documentField 값 생성 */
            String[] documentFields = wncol.COLLECTION_INFO[collectionIndex][RESULT_FIELD].split(",");

            List<Map<String, Map<String, String>>> fieldList = new ArrayList<>();
            for (int j = 0; j < resultCount; j++) {
                Map<String , String> fieldMap = new HashMap<String , String>();
                /* supermarketItemCode로 그룹화 하는 컬렉션 분기하여 결과값 생성 */
                for (String documentField : documentFields) {
                    if (!wncol.COLLECTION_INFO[collectionIndex][GROUP_BY].equals("")) {
                        fieldMap.put(documentField, wnsearch.getFieldInGroup(collections[i], documentField, j, 0));
                        itemCode = wnsearch.getFieldInGroup(collections[i], "itemCode", j, 0);
                    } else {
                        fieldMap.put(documentField, wnsearch.getField(collections[i], documentField, j, false));
                        itemCode = wnsearch.getField(collections[i], "itemCode", j, false);
                    }
                    fieldMap.put("expectedItem", expectedItemMap.get(itemCode) == null ? "N" : "Y");
                }

                Map<String , Map<String , String>> fieldListMap = new HashMap<String , Map<String , String>>();
                fieldListMap.put("field" , fieldMap);
                fieldList.add(fieldListMap);
            }

            countJsonObject.put("Document", fieldList);
            documentset.put("CollectionId", collections[i]);
            documentset.put("Documentset", countJsonObject);
            collectionJsonArray.add(documentset);
        }
        searchResultMap.put("Collection", collectionJsonArray);

        return searchResultMap;
    }

    /**
     * 찜목록 호출
     *
     * @param collection
     * @return 검색 결과값
     */
    private HashMap<String, String> getExpectedItems(String collection, Search search){
        HashMap<String,String> expectedItemMap = new HashMap<>();
        String url = "";

        if(collection.equals("wine25_gs")) url = "https://b2c-apigw.woodongsdev.com/refrigerator/v1/wine25/purchase/estimated/items";
        else if(collection.equals("woodel_gs")) url = "https://b2c-apigw.woodongsdev.com/thepop/v1/wdelivery/expected/items?brandDivisionCode=02";
        else if(collection.equals("woodel_mart")) url = "https://b2c-apigw.woodongsdev.com/thepop/v1/wdelivery/expected/items?brandDivisionCode=03";
        else return expectedItemMap;

        BufferedReader in = null;
        try{
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection)obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization",search.getToken());
            con.setConnectTimeout(1*1000);

            in = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));

            String line;
            String expectedAPIResult = "";
            while((line = in.readLine()) != null) { // response를 차례대로 출력
                expectedAPIResult += line;
            }

            JSONParser parser = new JSONParser();
            JSONArray jsonArray ;
            JSONObject jsonObject = (JSONObject) parser.parse(expectedAPIResult);

            if(collection.equals("wine25_gs")){
                jsonArray = (JSONArray) jsonObject.get("data");
            }else{
                JSONObject jsonObject1 = (JSONObject) jsonObject.get("data");
                jsonArray = (JSONArray) jsonObject1.get("expectedItemList");
            }

            for(int i=0; i<jsonArray.size(); i++){
                JSONObject jsonObject2 = (JSONObject) jsonArray.get(i);
                String itemCode = (String) jsonObject2.get("itemCode");
                expectedItemMap.put(itemCode, "Y");
            }
        }catch (Exception e){
            System.out.println("[error] expectedItem API Error !! (made by GSRetail)");
        } finally {
            if(in != null) try { in.close(); } catch(Exception e) { e.printStackTrace(); }
        }
        return expectedItemMap;
    }

    /**
     * 카테고리 결과 생성 및 정렬
     *
     * @param wnsearch
     * @param collections
     * @return 검색 결과값
     */
    private Object getCategoryList(WNSearch wnsearch, String[] collections) {
        Map<String, Integer> categoryListMap = new HashMap<>();
        for (int i = 0; i < collections.length; i++) {
            /* collection index 확인 */
            int collectionIndex = wnsearch.getCollIdx(collections[i]);
            /* 카테고리 리스트 출력 */
            String[] categoryFieldList = wncol.COLLECTION_INFO[collectionIndex][CATEGORY_GROUPBY].split("\\|");
            /* categoryGroupBy 필드명 */
            String categoryField = categoryFieldList[1].split(":")[0];
            /* categoryGroupBy dept */
            String categoryDept = categoryFieldList[1].split(":")[1];

            int dept = 1;
            for (int a = 0; a < categoryDept.length(); a++) {
                if (categoryDept.charAt(a) == ',') dept++;
            }

            int categoryCount = wnsearch.getCategoryCount(collections[i], categoryField, dept);

            /* 컬렉션 별 카테고리 리스트 저장 */
            for (int a = 0; a < categoryCount; a++) {
                String categoryName = wnsearch.getCategoryName(collections[i], categoryField, dept, a);
                int categoryCnt = wnsearch.getDocumentCountInCategory(collections[i], categoryField, dept, a);
                if(!categoryName.equals("null")) categoryListMap.put(categoryName, categoryCnt);
            }
        }

        /* 카테고리 리스트 내림 차순 정렬 */
        List<Map.Entry<String, Integer>> entryCategoryList = new LinkedList<>(categoryListMap.entrySet());
        entryCategoryList.sort((o1, o2) -> o2.getValue() - o1.getValue());

        List<String> categoryList = new ArrayList<>();

        /* 정렬된 카테고리 리스트 list 저장 */
        for (Map.Entry<String, Integer> list : entryCategoryList) {
            categoryList.add(list.getKey());
        }
        return categoryList;
    }
}
