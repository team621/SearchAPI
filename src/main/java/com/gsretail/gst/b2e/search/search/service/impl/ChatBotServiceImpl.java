package com.gsretail.gst.b2e.search.search.service.impl;

import java.util.*;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import com.gsretail.gst.b2e.search.search.common.*;
import com.gsretail.gst.b2e.search.search.model.Search;
import com.gsretail.gst.b2e.search.search.service.ChatBotService;
import static com.gsretail.gst.b2e.search.search.common.WNDefine.*;

/**
 * com.gsretail.gst.b2e.search.search.service.impl
 * ChatBotServiceImpl
 *
 * @author : WISENUT
 * @date : 2022-06-22
 * @tags :
 */
@Service
public class ChatBotServiceImpl implements ChatBotService {
    /* 디버깅 보기 설정 */
    boolean isDebug = false;
    /* 검색엔진 설정 객체 생성 */
    WNUtils wnUtils = new WNUtils();
    WNCollection wncol = new WNCollection();
    @Override
    public JSONObject getChatBotSearch(Search search) {
        String[] collections = {"oneplus","thefresh","woodel_gs","woodel_mart","gs25_reservation","wine25_gs","giftcard"};
        String[] searchFields = null;
        JSONObject searchResult = new JSONObject();
        for (String collection : collections) {

        }



        /* 머지 컬렉션 호출 */
        String[] mergeCollections = wncol.MERGE_COLLECTIONS;

        /* 검색 엔진 객체 생성 */
        WNSearch wnsearch = new WNSearch(isDebug, false, collections, mergeCollections , searchFields);

        /* Collection 설정 */
        setCollectionInfo(wnsearch, collections, search);

        /* MergeCollection 설정 */
        for(int i = 0 ; i < mergeCollections.length ; i ++){
            wnsearch.setMergeCollectionInfoValue(mergeCollections[i] , MERGE_PAGE_INFO,search.getStartCount() + "," + search.getListCount());
        }

        /* 검색 수행 */
        wnsearch.search(search.getQuery(), false, CONNECTION_CLOSE, false , true);

        /* 검색 결과 생성 */
        Map<String , Object> searchResultMap = getSearchResult(wnsearch, mergeCollections, search);

        searchResult.put("SearchQueryResult", searchResultMap);

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
        for(int i = 0 ; i < collections.length ; i++) {
            //검색어가 없으면 DATE_RANGE 로 전체 데이터 출력
            if (!"".equals(search.getQuery())) {
                wnsearch.setCollectionInfoValue(collections[i], SORT_FIELD, search.getSort() + ",exposureSeq/DESC");
            } else {
                wnsearch.setCollectionInfoValue(collections[i], DATE_RANGE, search.getStartDate().replaceAll("[.]", "/") + ",2030/01/01,-");
                wnsearch.setCollectionInfoValue(collections[i], SORT_FIELD, search.getSort() + ",exposureSeq/DESC");
            }

            wnsearch.setCollectionInfoValue(collections[i], SEARCH_FIELD, "itemName,shortItemName");

            wnsearch.setCollectionInfoValue(collections[i],CATEGORY_GROUPBY , "");

            wnsearch.setCollectionInfoValue(collections[i],GROUP_BY , "supermarketItemCode,1");
            wnsearch.setCollectionInfoValue(collections[i],GROUP_SORT_FIELD , search.getSort() + ",exposureSeq/DESC");
        }
    }

    /**
     * 검색결과 생성
     *
     * @param wnsearch
     * @param mergeCollections
     * @param search
     * @return 검색 결과값
     */
    private Map<String, Object> getSearchResult(WNSearch wnsearch, String[] mergeCollections, Search search) {
        Map<String, Object> searchResultMap = new HashMap<>();
        searchResultMap.put("Query", search.getQuery());
        searchResultMap.put("Version", "5.3.0");

        for(int i = 0 ; i < mergeCollections.length ; i++){
            Map<String , Object> collectionMap = new HashMap<>();

            int searchResultCount = 0;
            int searchResultTotalCount = 0;

            searchResultCount = wnsearch.getResultGroupCount(mergeCollections[i]) < 0 ? 0 : wnsearch.getResultGroupCount(mergeCollections[i]);
            searchResultTotalCount = wnsearch.getResultTotalGroupCount(mergeCollections[i]) < 0 ? 0 : wnsearch.getResultTotalGroupCount(mergeCollections[i]);

            int mergeCollectionidx = wnsearch.getMergeCollIdx(mergeCollections[i]);
            String[] documentField = wncol.MERGE_COLLECTION_INFO[mergeCollectionidx][MERGE_RESULT_FIELD].split(",");

            List<Map<String , String>> resultFieldList = new ArrayList<>();

            for(int j = 0 ; j < searchResultCount ; j++){
                Map<String , String> field = new HashMap<>();
                for(int a = 0 ; a < documentField.length ; a++){
                    String documentFieldName = documentField[a];
                    field.put(documentFieldName ,wnsearch.getFieldInGroup(mergeCollections[i], documentFieldName , j , 0));
                }
                field.put("Rank" , Integer.toString(j));
                resultFieldList.add(field);
            }
            collectionMap.put("Document" , resultFieldList);
            collectionMap.put("ResultCount" , searchResultCount);
            collectionMap.put("TotalCount" , searchResultTotalCount);
            searchResultMap.put("DocumentSet" , collectionMap);
        }
        return searchResultMap;
    }
}
