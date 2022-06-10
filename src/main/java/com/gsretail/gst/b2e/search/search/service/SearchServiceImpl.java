/*
 * Copyright (c) GS Retail.
 * All rights reserved.
 * 모든 권한은 GS리테일에 있으며,
 * GS리테일의 허락없이 소스 및 이진형식을 재배포, 사용하는 행위를 금지합니다.
 */

package com.gsretail.gst.b2e.search.search.service;

import com.gsretail.gst.b2e.search.search.common.WNCollection;
import com.gsretail.gst.b2e.search.search.common.WNSearch;
import com.gsretail.gst.b2e.search.search.model.Search;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.gsretail.gst.b2e.search.search.common.WNDefine.*;

/**
 * com.gsretail.gst.b2e.search.search.service
 * SearchServiceImpl
 *
 * @author : WISENUT
 * @date : 2022-05-17
 * @tags :
 */
@Service
public class SearchServiceImpl implements SearchService {
    //기존 검색어 (오타 검색)
    String typoQuery = "";
    //오타검색을 위한 전체 검색 결과 카운트
    int allTotalCount = 0;
    //디버깅 보기 설정
    boolean isDebug = false;

    /**
     * 통합검색
     *
     * @param search     검색 객체
     * @return
     */
    public JSONObject getTotalSearch(Search search) {
        //정타 추천으로도 검색 결과가 없을 경우를 위해 전체 검색 결과 수 초기화
        allTotalCount = 0;
        //오타 후 추천 검색어 화면 출력 여부 체크
        boolean useSuggestedQuery = true;

        WNCollection wncol = new WNCollection();
        String collection = search.getCollection();
        String[] searchFields = null;
        String[] collections = null;

        if (collection.equals("ALL")) { //통합검색인 경우
            collections = wncol.COLLECTIONS;
        } else {                        //개별 또는 다중 검색인 경우
            collections = collection.split(",");
        }

        WNSearch wnsearch = new WNSearch(isDebug, false, collections, searchFields);

        //컬렉션 설정
        setCollectionInfo(wnsearch, collections, search, "totalSearch");

        //검색 수행
        wnsearch.search(search.getQuery(), false, CONNECTION_CLOSE, useSuggestedQuery , true);

        //검색결과 생성
        Map<String , Object> totalSearchResultMap = getSearchResult(wnsearch, wncol, collections, search);

        //카테고리 리스트 생성 후 결과값 저장
        totalSearchResultMap.put("categoryList" , getCategoryList(wnsearch , wncol , collections));

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
        if (isDebug) {
            System.out.println(debugMsg.replace("<br>", "\n"));
        }

        return searchResult;
    }

    /**
     * 딜리버리 깔대기 검색
     *
     * @param search    검색 객체
     * @return
     */
    public JSONObject getDeliveryStoreSearch(Search search) {
        //오타 후 추천 검색어 화면 출력 여부 체크
        boolean useSuggestedQuery = false;

        WNCollection wncol = new WNCollection();
        String collection = search.getCollection();
        String[] searchFields = null;
        String[] collections = null;

        if (collection.equals("ALL")) { //통합검색인 경우
            collections = wncol.COLLECTIONS;
        } else {                        //개별 또는 다중 검색인 경우
            collections = collection.split(",");
        }

        WNSearch wnsearch = new WNSearch(isDebug, false, collections, searchFields);

        //컬렉션 정보 설정
        setCollectionInfo(wnsearch, collections, search, "storeSearch");

        //1. 조건에 맞는 매장 코드 검색 수행 (queryLog 출력안함)
        wnsearch.search(search.getQuery(), false, CONNECTION_REUSE, useSuggestedQuery , false);

        //1번 검색 수행 결과 이용한 상품출력할 매장 코드 생성
        String storeCode = "";
        for (int i = 0; i < collections.length; i++) {
            int resultCount = wnsearch.getResultGroupCount(collections[i]);
            System.out.println("resultCount = " + resultCount);
            for (int j = 0; j < resultCount; j++) {
                if (resultCount == 1 || j + 1 == resultCount) {
                    storeCode += wnsearch.getFieldInGroup(collections[i], "storeCode", j, 0);
                } else {
                    storeCode += wnsearch.getFieldInGroup(collections[i], "storeCode", j, 0) + "|";
                }
            }
        }
        //통합검색에 사용될 storeCode 저장
        search.setStoreCode(storeCode);

        //통합검색은 선택된 매장의 상품목록 검색으로 상품코드 삭제
        search.setSupermarketItemCode("");

        //컬렉션 정보 설정
        setCollectionInfo(wnsearch, collections, search, "totalSearch");

        //통합검색 수행
        wnsearch.search(search.getQuery(), false, CONNECTION_CLOSE, useSuggestedQuery , true);

        //결과값 생성
        Map<String , Object> totalSearchResultMap = getSearchResult(wnsearch, wncol, collections, search);

        JSONObject searchResult = new JSONObject();
        searchResult.put("SearchQueryResult", totalSearchResultMap);

        // 디버그 메시지 출력
        String debugMsg = wnsearch.printDebug() != null ? wnsearch.printDebug().trim() : "";
        if (isDebug) {
            System.out.println(debugMsg.replace("<br>", "\n"));
        }

        return searchResult;
    }

    /**
     * 컬렉션 정보 설정
     *
     * @param wnsearch
     * @param collections
     * @param search
     * @param flag          통검 : totalSearch , 딜리버리 깔대기 : storeSearch
     */
    public void setCollectionInfo(WNSearch wnsearch, String[] collections, Search search, String flag) {
        for (int i = 0; i < collections.length; i++) {

            //flag 값 이용한 통합검색 , 딜러비리 깔대기 검색 출력건수 생성 및 설정
            if (flag.equals("storeSearch")) {
                int storeCount = 1;
                String storeCodeStr = search.getStoreCode();
                System.out.println("storeCodeStr = " + storeCodeStr);
                for (int j = 0; j < storeCodeStr.length(); j++) {
                    if (storeCodeStr.charAt(j) == '|') storeCount++;
                }
                System.out.println("storecout = " + storeCount);
                wnsearch.setCollectionInfoValue(collections[i], PAGE_INFO, 0 + "," + storeCount * 2);
            } else {
                wnsearch.setCollectionInfoValue(collections[i], PAGE_INFO, search.getStartCount() + "," + search.getListCount());
            }


            //검색어가 없으면 DATE_RANGE 로 전체 데이터 출력
            if (!"".equals(search.getQuery())) {
                wnsearch.setCollectionInfoValue(collections[i], SORT_FIELD, search.getSort() + ",exposureSeq/DESC");
            } else {
                wnsearch.setCollectionInfoValue(collections[i], DATE_RANGE, search.getStartDate().replaceAll("[.]", "/") + ",2030/01/01,-");
                wnsearch.setCollectionInfoValue(collections[i], SORT_FIELD, search.getSort());
            }

            //searchField 값이 있으면 설정, 없으면 기본검색필드
            if (!"".equals(search.getSearchField()) && search.getSearchField().indexOf("ALL") == -1) {
                wnsearch.setCollectionInfoValue(collections[i], SEARCH_FIELD, search.getSearchField());
            }

            //exquery 생성
            String exquery = "";

            //딜리버리 깔대기 검색시 사용될 exquery 생성
            if (flag.equals("storeSearch")) {
                if (!"".equals(search.getStoreCode())) {
                    exquery += "<storeCode:contains:" + search.getStoreCode() + ">";
                }

                if (!"".equals(search.getSupermarketItemCode())) {
                    exquery += " <supermarketItemCode:contains:" + search.getSupermarketItemCode() + ">";
                }
            } else { //통합검색에 사용될 exquery 생성
                exquery = setExquery(search);
            }

            //exquery 설정
            if (!"".equals(exquery)) {
                wnsearch.setCollectionInfoValue(collections[i], EXQUERY_FIELD, exquery);
            }

            //filterquery 설정
            wnsearch.setCollectionInfoValue(collections[i], FILTER_OPERATION, "<sellPrice:gt:" + search.getMinSellPrice() + "> <sellPrice:lt:" + search.getMaxSellPrice() + ">");

            //categoryquery 설정
            if (!"".equals(search.getCategoryId())) {
                wnsearch.setCollectionInfoValue(collections[i], CATEGORY_QUERY, "categoryId|" + search.getCategoryId());
            }

            //통합검색시 supermarketItemCode(상품 고유 번호) 이용하여 그룹화
            if (!flag.equals("storeSearch")) {
                if (collections[i].equals("thefresh") || collections[i].equals("woodel_gs")) {
                    wnsearch.setCollectionInfoValue(collections[i], GROUP_BY, "supermarketItemCode,1");
                    wnsearch.setCollectionInfoValue(collections[i], GROUP_SORT_FIELD, search.getSort() + ",exposureSeq/DESC");
                }
            }else if(flag.equals("storeSearch")){
                wnsearch.setCollectionInfoValue(collections[i], GROUP_BY, "storeCode,1");
                wnsearch.setCollectionInfoValue(collections[i], GROUP_SORT_FIELD, search.getSort() + ",exposureSeq/DESC");
            }
        }
    }

    /**
     * 검색결과 생성
     *
     * @param wnsearch
     * @param wncol
     * @param collections
     * @param search
     * @return 검색 결과값
     */
    public Map<String , Object> getSearchResult(WNSearch wnsearch, WNCollection wncol, String[] collections, Search search) {
        Map<String , Object> searchResultMap = new HashMap<>();
        JSONArray collectionJsonArray = new JSONArray();

        searchResultMap.put("query", search.getQuery());
        searchResultMap.put("Version", "5.3.0");
        searchResultMap.put("typoQuery", typoQuery);
        searchResultMap.put("selectStoreCode", search.getStoreCode());

        //컬렉션별 검색 결과 생성
        for (int idx = 0; idx < collections.length; idx++) {
            JSONObject documentset = new JSONObject();
            int resultCount = 0;
            int totalCount = 0;

            //supermarketItemCode로 그룹화 하는 컬렉션 선별하여 검색 결과 count 생성
            if (collections[idx].equals("oneplus") || collections[idx].equals("상품권") || collections[idx].equals("매장") || collections[idx].equals("아동급식")) {
                resultCount = wnsearch.getResultCount(collections[idx]);
                totalCount = wnsearch.getResultTotalCount(collections[idx]);
            } else {
                resultCount = wnsearch.getResultGroupCount(collections[idx]);
                totalCount = wnsearch.getResultTotalGroupCount(collections[idx]);
            }

            //오타 검색 결과값 확인용
            allTotalCount += totalCount;

            JSONObject countJsonObject = new JSONObject();
            countJsonObject.put("resultCount", resultCount);
            countJsonObject.put("totalCount", totalCount);

            JSONArray documentJsonArray = new JSONArray();

            //선택된 컬렉션이 WNCollection 몇번째 인지 index값
            int collectionIndex = wnsearch.getCollIdx(collections[idx]);
            //선택된 컬렉션 documentField 값 생성
            String[] documentFields = wncol.COLLECTION_INFO[collectionIndex][RESULT_FIELD].split(",");

            List<Map<String, Map<String, String>>> fieldList = new ArrayList<>();
            for (int i = 0; i < resultCount; i++) {
                Map<String , String> fieldMap = new HashMap<String , String>();

                //supermarketItemCode로 그룹화 하는 컬렉션 분기하여 결과값 생성
                for (String documentField : documentFields) {
                    if (collections[idx].equals("oneplus") || collections[idx].equals("상품권") || collections[idx].equals("매장") || collections[idx].equals("아동급식")) {
                        fieldMap.put(documentField, wnsearch.getField(collections[idx], documentField, i, false));
                    } else {
                        fieldMap.put(documentField, wnsearch.getFieldInGroup(collections[idx], documentField, i, 0));
                    }
                }

                Map<String , Map<String , String>> fieldListMap = new HashMap<String , Map<String , String>>();

                fieldListMap.put("field" , fieldMap);

                fieldList.add(fieldListMap);

            }

            countJsonObject.put("Document", fieldList);

            documentset.put("CollectionId", collections[idx]);

            documentset.put("Documentset", countJsonObject);

            collectionJsonArray.add(documentset);
        }
        searchResultMap.put("Collection", collectionJsonArray);

        return searchResultMap;
    }

    private Object getCategoryList(WNSearch wnsearch, WNCollection wncol, String[] collections) {
        Map<String, Integer> categoryListMap = new HashMap<>();
        for (int idx = 0; idx < collections.length; idx++) {
            //collection index 확인
            int collectionIndex = wnsearch.getCollIdx(collections[idx]);
            //카테고리 리스트 출력
            String[] categoryFieldList = wncol.COLLECTION_INFO[collectionIndex][CATEGORY_GROUPBY].split("\\|");
            //categoryGroupBy 필드명
            String categoryField = categoryFieldList[1].split(":")[0];
            //categoryGroupBy dept
            String categoryDept = categoryFieldList[1].split(":")[1];

            int dept = 1;
            for (int a = 0; a < categoryDept.length(); a++) {
                if (categoryDept.charAt(a) == ',') dept++;
            }

            int categoryCount = wnsearch.getCategoryCount(collections[idx], categoryField, dept);

            //컬렉션 별 카테고리 리스트 저장
            for (int a = 0; a < categoryCount; a++) {
                String categoryName = wnsearch.getCategoryName(collections[idx], categoryField, dept, a);
                int categoryCnt = wnsearch.getDocumentCountInCategory(collections[idx], categoryField, dept, a);
                categoryListMap.put(categoryName, categoryCnt);
            }
        }

        //카테고리 리스트 내림 차순 정렬
        List<Map.Entry<String, Integer>> entryCategoryList = new LinkedList<>(categoryListMap.entrySet());
        entryCategoryList.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        List<String> categoryList = new ArrayList<>();

        //정렬된 카테고리 리스트 list 저장
        for (Map.Entry<String, Integer> list : entryCategoryList) {
            categoryList.add(list.getKey());
        }

        return categoryList;
    }

    /**
     * exquery 여부 확인
     *
     * @param search
     * @return
     */
    public String setExquery(Search search) {
        String exquery = "";

        String serviceCode = search.getServiceCode();
        if (!serviceCode.equals("")) exquery += mkExqueryString(serviceCode, "serviceCode");

        String tag = search.getTag();
        if (!tag.equals("")) exquery += mkExqueryString(tag, "tag");

        String discountTag = search.getDiscountTag();
        if (!discountTag.equals("")) exquery += mkExqueryString(discountTag, "discountTag");

        String serviceTag = search.getServiceTag();
        if (!serviceTag.equals("")) exquery += mkExqueryString(serviceTag, "serviceTag");

        String wine25ItemKindName = search.getWine25ItemKindName();
        if (!wine25ItemKindName.equals("")) exquery += mkExqueryString(wine25ItemKindName, "wine25ItemKindName");

        String wine25RegionSpName = search.getWine25RegionSpName();
        if (!wine25RegionSpName.equals("")) exquery += mkExqueryString(wine25RegionSpName, "wine25RegionSpName");

        String cardDiscountName = search.getCardDiscountName();
        if (!cardDiscountName.equals("")) exquery += mkExqueryString(cardDiscountName, "cardDiscountName");

        String cardDiscountYn = search.getCardDiscountYn();
        if (!cardDiscountYn.equals("")) exquery += mkExqueryString(cardDiscountYn, "cardDiscountYn");

        String adultYn = search.getAdultYn();
        if (!adultYn.equals("")) exquery += mkExqueryString(adultYn, "adultYn");

        String soldOutSp = search.getSoldOutSp();
        if (!soldOutSp.equals("")) exquery += mkExqueryString(soldOutSp, "soldOutSp");

        String deliverySp = search.getDeliverySp();
        if (!deliverySp.equals("")) exquery += mkExqueryString(deliverySp, "deliverySp");

        String stockCheckYn = search.getStockCheckYn();
        if (!stockCheckYn.equals("")) exquery += mkExqueryString(stockCheckYn, "stockCheckYn");

        String recommendItemYn = search.getRecommendItemYn();
        if (!recommendItemYn.equals("")) exquery += mkExqueryString(recommendItemYn, "recommendItemYn");

        String storeCode = search.getStoreCode();
        if (!"".equals(storeCode)) exquery += mkExqueryString(storeCode, "storeCode");

        String prmByQty = search.getPrmByQty();
        if (!"".equals(prmByQty)) exquery += mkExqueryString(prmByQty, "prmByQty");

        String prmGtQty = search.getPrmGtQty();
        if (!"".equals(prmGtQty)) exquery += mkExqueryString(prmByQty, "prmGtQty");

        String stockSupermarketItemCode = search.getStockSupermarketItemCode();
        if (!"".equals(stockSupermarketItemCode))
            exquery += mkExqueryString(stockSupermarketItemCode, "supermarketItemCode");

        return exquery;
    }

    /**
     * exquery 생성
     *
     * @param parameter
     * @param prefixFieldName
     * @return String exquery
     */
    public String mkExqueryString(String parameter, String prefixFieldName) {
        String exquery = "";

        String[] parameterArrays = parameter.split(",");
        exquery += "(";
        for (int i = 0; i < parameterArrays.length; i++) {
            if (i == parameterArrays.length - 1)
                exquery += "<" + prefixFieldName + ":contains:" + parameterArrays[i] + ">";
            else exquery += "<" + prefixFieldName + ":contains:" + parameterArrays[i] + "> | ";
        }
        exquery += ") ";

        return exquery;
    }
}

