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

import java.io.File;
import java.io.FileReader;
import java.net.InetAddress;
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

    public JSONObject getTotalSearch(Search search) {
        //정타 추천으로도 검색 결과가 없을 경우를 위해 전체 검색 결과 수 초기화
        allTotalCount = 0;
        //오타 후 추천 검색어 화면 출력 여부 체크
        boolean useSuggestedQuery = true;

        JSONObject searchResultJson = new JSONObject();

        WNCollection wncol = new WNCollection();
        String collection = search.getCollection();
        String[] searchFields = null;
        String[] collections = null;

        if (collection.equals("ALL")) { //통합검색인 경우
            collections = wncol.COLLECTIONS;
        } else {                        //개별검색인 경우
            collections = collection.split(",");
        }

        WNSearch wnsearch = new WNSearch(isDebug, false, collections, searchFields);

        //컬렉션 설정
        setCollectionInfo(wnsearch, collections, search, "totalSearch");

        wnsearch.search(search.getQuery(), false, CONNECTION_CLOSE, useSuggestedQuery);

        JSONObject SearchQueryResultJson = getSearchResult(wnsearch, wncol, collections, search);

        searchResultJson.put("SearchQueryResult", SearchQueryResultJson);

        //오타에 대한 정타 추천 검색어
        String typoSearch = search.getTypoSearch();
        String suggestedQuery = wnsearch.suggestedQuery;

        //오타검색 (전체 검색 결과가 없고 오타 수정 단어가 있을 경우, 오타검색일경우)
        if(allTotalCount <= 0 && !suggestedQuery.equals("") && typoSearch.equals("N")) {
            //오타 검색어(기존 검색어) 저장
            typoQuery = search.getQuery();

            //검색어 (오타 → 정타 추천) 수정
            search.setQuery(suggestedQuery);
            JSONObject searchResultJsonTemp = getTotalSearch(search);

            if(allTotalCount > 0)  searchResultJson = searchResultJsonTemp;
        }
        SearchQueryResultJson.put("typoQuery", typoQuery);
        //오타 검색어 초기화
        typoQuery = "";

        // 디버그 메시지 출력
        String debugMsg = wnsearch.printDebug() != null ? wnsearch.printDebug().trim() : "";
        if (isDebug) {
            System.out.println(debugMsg.replace("<br>", "\n"));
        }
        return searchResultJson;
    }

    public JSONObject getDeliveryStoreSearch(Search search) {
        //오타 후 추천 검색어 화면 출력 여부 체크
        boolean useSuggestedQuery = false;

        JSONObject searchResultJson = new JSONObject();
        WNCollection wncol = new WNCollection();
        String collection = search.getCollection();
        String[] searchFields = null;
        String[] collections = null;

        if (collection.equals("ALL")) { //통합검색인 경우
            collections = wncol.COLLECTIONS;
        } else {                        //개별검색인 경우
            collections = collection.split(",");
        }

        WNSearch wnsearch = new WNSearch(isDebug, false, collections, searchFields);

        //컬렉션 설정
        setCollectionInfo(wnsearch, collections, search, "storeSearch");

        wnsearch.search(search.getQuery(), false, CONNECTION_REUSE, useSuggestedQuery);

        String storeCode = "";
        for (int i = 0; i < collections.length; i++) {
            int resultCount = wnsearch.getResultCount(collections[i]);
            for (int j = 0; j < resultCount; j++) {
                if (resultCount == 1 || j + 1 == resultCount) {
                    storeCode += wnsearch.getField(collections[i], "storeCode", j, false);
                } else {
                    storeCode += wnsearch.getField(collections[i], "storeCode", j, false) + "|";
                }
            }
        }

        search.setStoreCode(storeCode);

        setCollectionInfo(wnsearch, collections, search, "totalSearch");

        wnsearch.search(search.getQuery(), false, CONNECTION_CLOSE, useSuggestedQuery);

        searchResultJson.put("SearchQueryResult", getSearchResult(wnsearch, wncol, collections, search));

        // 디버그 메시지 출력
        String debugMsg = wnsearch.printDebug() != null ? wnsearch.printDebug().trim() : "";
        if (isDebug) {
            System.out.println(debugMsg.replace("<br>", "\n"));
        }

        return searchResultJson;
    }

    public void setCollectionInfo(WNSearch wnsearch, String[] collections, Search search, String flag) {
        for (int i = 0; i < collections.length; i++) {

            //출력건수
            if (flag.equals("storeSearch")) {
                int storeCount = 1;
                String storeCodeStr = search.getStoreCode();
                for (int j = 0; j < storeCodeStr.length(); j++) {
                    if (storeCodeStr.charAt(i) == '|') storeCount++;
                }
                wnsearch.setCollectionInfoValue(collections[i], PAGE_INFO, search.getStartCount() + "," + storeCount * 2);
            } else {
                wnsearch.setCollectionInfoValue(collections[i], PAGE_INFO, search.getStartCount() + "," + search.getListCount());
            }


            //검색어가 없으면 DATE_RANGE 로 전체 데이터 출력
            if (!"".equals(search.getQuery())) {
                wnsearch.setCollectionInfoValue(collections[i], SORT_FIELD, search.getSort() + ",exposureSeq/DESC");
            } else {
                wnsearch.setCollectionInfoValue(collections[i], DATE_RANGE, search.getStartDate().replaceAll("[.]", "/") + ",2030/01/01,-");
                wnsearch.setCollectionInfoValue(collections[i], SORT_FIELD, "DATE/DESC");
            }

            //searchField 값이 있으면 설정, 없으면 기본검색필드
            if (!"".equals(search.getSearchField()) && search.getSearchField().indexOf("ALL") == -1) {
                wnsearch.setCollectionInfoValue(collections[i], SEARCH_FIELD, search.getSearchField());
            }

            String exquery = "";
            if (flag.equals("storeSearch")) {
                if (!"".equals(search.getStoreCode())) {
                    exquery += "<storeCode:contains:" + search.getStoreCode() + ">";
                }

                if (!"".equals(search.getSupermarketItemCode())) {
                    exquery += " <supermarketItemCode:contains:" + search.getSupermarketItemCode() + ">";
                }
            } else {
                exquery = setExquery(search);
            }

            //exquery 설정
            if (!"".equals(exquery)) {
                wnsearch.setCollectionInfoValue(collections[i], EXQUERY_FIELD, exquery);
            }

            wnsearch.setCollectionInfoValue(collections[i], FILTER_OPERATION, "<sellPrice:gt:" + search.getMinSellPrice() + "> <sellPrice:lt:" + search.getMaxSellPrice() + ">");

            if (!"".equals(search.getCategoryId())) {
                wnsearch.setCollectionInfoValue(collections[i], CATEGORY_QUERY, search.getCategoryId());
            }

            if (!flag.equals("storeSearch")) {
                if (collections[i].equals("thefresh")) {
                    wnsearch.setCollectionInfoValue(collections[i], GROUP_BY, "supermarketItemCode,1");
                    wnsearch.setCollectionInfoValue(collections[i], GROUP_SORT_FIELD, search.getSort() + ",exposureSeq/DESC");
                }
            }
        }
    }

    public JSONObject getSearchResult(WNSearch wnsearch, WNCollection wncol, String[] collections, Search search) {
        JSONObject SearchQueryResultJson = new JSONObject();
        JSONArray collectionJsonArray = new JSONArray();
        Map<String, Integer> categoryListMap = new LinkedHashMap<String, Integer>();

        SearchQueryResultJson.put("query", search.getQuery());
        SearchQueryResultJson.put("Version", "5.3.0");

        for (int idx = 0; idx < collections.length; idx++) {
            JSONObject documentset = new JSONObject();
            int resultCount = 0;
            int totalCount = 0;

            if (collections[idx].equals("oneplus") || collections[idx].equals("상품권") || collections[idx].equals("매장") || collections[idx].equals("아동급식")) {
                resultCount = wnsearch.getResultCount(collections[idx]);
                totalCount = wnsearch.getResultTotalCount(collections[idx]);
            } else {
                resultCount = wnsearch.getResultGroupCount(collections[idx]);
                totalCount = wnsearch.getResultTotalGroupCount(collections[idx]);
            }

            allTotalCount += totalCount;

            JSONObject countJsonObject = new JSONObject();
            countJsonObject.put("resultCount", resultCount);
            countJsonObject.put("totalCount", totalCount);

            JSONArray documentJsonArray = new JSONArray();
            int collectionIndex = wnsearch.getCollIdx(collections[idx]);
            String[] documentFields = wncol.COLLECTION_INFO[collectionIndex][RESULT_FIELD].split(",");

            for (int i = 0; i < resultCount; i++) {
                JSONObject searchResultJsonObject = new JSONObject();
                searchResultJsonObject.put("collectionId", collections[idx]);
                JSONObject fieldJsonObject = new JSONObject();

                for (String documentField : documentFields) {
                    if (collections[idx].equals("oneplus") || collections[idx].equals("상품권") || collections[idx].equals("매장") || collections[idx].equals("아동급식")) {
                        fieldJsonObject.put(documentField, wnsearch.getField(collections[idx], documentField, i, false));
                    } else {
                        fieldJsonObject.put(documentField, wnsearch.getFieldInGroup(collections[idx], documentField, i, 0));
                    }
                }
                searchResultJsonObject.put("field", fieldJsonObject);

                documentJsonArray.add(searchResultJsonObject);
            }

            String[] categoryes = wncol.COLLECTION_INFO[collectionIndex][CATEGORY_GROUPBY].split("\\|");
            String categoryField = categoryes[1].split(":")[0];
            String categoryDept = categoryes[1].split(":")[1];

            int dept = 1;
            for (int a = 0; a < categoryDept.length(); a++) {
                if (categoryDept.charAt(a) == ',') dept++;
            }

            int categoryCount = wnsearch.getCategoryCount(collections[idx], categoryField, dept);

            for (int a = 0; a < categoryCount; a++) {
                String categoryName = wnsearch.getCategoryName(collections[idx], categoryField, dept, a);
                int categoryCnt = wnsearch.getDocumentCountInCategory(collections[idx], categoryField, dept, a);
                categoryListMap.put(categoryName, categoryCnt);
            }

            countJsonObject.put("Document", documentJsonArray);

            documentset.put("id", collections[idx]);

            documentset.put("selectStoreCode", search.getStoreCode());

            documentset.put("Documentset", countJsonObject);

            collectionJsonArray.add(documentset);
        }

        List<Map.Entry<String, Integer>> entryList = new LinkedList<>(categoryListMap.entrySet());
        entryList.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        List<String> categoryList = new ArrayList<String>();

        for (Map.Entry<String, Integer> entry : entryList) {
            categoryList.add(entry.getKey());
        }

        SearchQueryResultJson.put("categoryList", categoryList);

        SearchQueryResultJson.put("Collection", collectionJsonArray);

        return SearchQueryResultJson;
    }

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

