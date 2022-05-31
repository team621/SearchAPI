/*
 * Copyright (c) GS Retail.
 * All rights reserved.
 * 모든 권한은 GS리테일에 있으며,
 * GS리테일의 허락없이 소스 및 이진형식을 재배포, 사용하는 행위를 금지합니다.
 */

package com.gsretail.gst.b2e.search.search.service;

import com.gsretail.gst.b2e.search.search.model.Search;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * com.gsretail.gst.b2e.search.search.service
 * SearchServiceImpl
 *
 * @author : WISENUT
 * @date : 2022-05-17
 * @tags :
 */
@Service
public class SearchServiceImpl implements SearchService{

    //기존 검색어 (오타 검색)
    String typoQuery = "";
    //오타검색을 위한 전체 검색 결과 카운트
    int allTotalCount = 0;

    public JSONObject getSearchResult(Search search){
        //검색 결과 return json 객체
        JSONObject searchResultJson = new JSONObject();
        JSONObject SearchQueryResultJson = new JSONObject();
        JSONArray collectionJsonArray = new JSONArray();

        SearchQueryResultJson.put("query",search.getQuery());
        SearchQueryResultJson.put("Version","5.3.0");

        //검색엔진 설정 프로퍼티 파일 생성
        Properties properties = getProperties();

        //SF-1 검색엔진 검색 객체 생성
        QueryAPI530.Search wnSearch = new QueryAPI530.Search();

        //컬렉션이 ALL 일 경우 전체 컬렉션으로 설정
        setCollectionInfoValue(search, "", "COLLECTION", properties);

        //컬렉션 리스트(배열) 구하기
        String[] collections = search.getArrays(search.getCollection());

        //검색 필드 체크 (ALL or 빈 값 확인)
        Boolean hasSearchField = true;
        if(search.getSearchField().equals("") || search.getSearchField().equals("ALL")) hasSearchField = false;

        for(int i=0; i<collections.length; i++) {
            JSONObject documentset = new JSONObject();

            //검색엔진 설정
            setSearchEngine(wnSearch,search,properties,collections[i],hasSearchField);

            int totalCount = wnSearch.w3GetResultTotalCount(collections[i]) <= 0 ? 0 : wnSearch.w3GetResultTotalCount(collections[i]);
            int resultCount = wnSearch.w3GetResultCount(collections[i]) <= 0 ? 0 : wnSearch.w3GetResultCount(collections[i]);

            //전체 컬렉션의 총 검색 건수
            allTotalCount += totalCount;

/*            int depth = 1;
            int groupCount = wnSearch.w3GetCategoryCount( collections[i], "categoryName", depth );
            System.out.println("groupCount = " + groupCount);

            for(int j=0; j<groupCount; j++){
                System.out.println("catename : " + wnSearch.w3GetCategoryName(collections[i], "categoryName", depth, j) +" ("+wnSearch.w3GetDocumentCountInCategory(collections[i], "categoryName", depth, j)+")");
            }*/

            JSONObject countJsonObject = new JSONObject();
            countJsonObject.put("resultCount",resultCount);
            countJsonObject.put("totalCount",totalCount);

            JSONArray documentJsonArray = new JSONArray();

            //출력필드 리스트(배열) 구하기
            String[] documentFields = search.getArrays(search.getDocumentField());

            //검색 결과
            for(int j=0; j<resultCount; j++){
                JSONObject searchResultJsonObject = new JSONObject();
                searchResultJsonObject.put("collectionId",collections[i]);
                JSONObject fieldJsonObject = new JSONObject();

                for (String documentField : documentFields) fieldJsonObject.put(documentField,wnSearch.w3GetField(collections[i], documentField,j));

                searchResultJsonObject.put("field",fieldJsonObject);

                documentJsonArray.add(searchResultJsonObject);
            }

            countJsonObject.put("Document",documentJsonArray);

            documentset.put("id",collections[i]);

            documentset.put("Documentset",countJsonObject);

            collectionJsonArray.add(documentset);
        }//end for (collections)

        SearchQueryResultJson.put("Collection", collectionJsonArray);

        searchResultJson.put("SearchQueryResult",SearchQueryResultJson);

        //오타에 대한 정타 추천 검색어
        String typoSearch = search.getTypoSearch();
        String suggestedQuery = wnSearch.w3GetSuggestedQuery();

        //오타검색 (전체 검색 결과가 없고 오타 수정 단어가 있을 경우, 오타검색일경우)
        if(allTotalCount <= 0 && !suggestedQuery.equals("") && typoSearch.equals("N")) {
            //오타 검색어(기존 검색어) 저장
            typoQuery = search.getQuery();
            //검색어 (오타 → 정타 추천) 수정
            //search.setQuery(suggestedQuery);

            //JSONObject searchResultJsonTemp = getSearchResult(search);
            //if(allTotalCount > 0)  searchResultJson = searchResultJsonTemp;
        }
        //오타 검색어에 대한 검색 결과 호출
        //정타 추천으로도 검색 결과가 없을 경우를 위해 전체 검색 결과 수 초기화
        allTotalCount = 0;

        //SearchQueryResultJson.put("typoQuery", typoQuery);
        //오타 검색어 초기화
        typoQuery = "";

        wnSearch.w3CloseServer();

        return searchResultJson;
    }//end getSearchResult

    /**
     * 프로퍼티 파일 호출
     *
     * @return the properties
     */
    public Properties getProperties(){
        Properties properties = new Properties();
        InetAddress inetAddress = null;
        try{
            inetAddress = inetAddress.getLocalHost();
            String ip = inetAddress.getHostAddress();
            if(ip.equals("27.122.138.23")) properties.load(new FileReader("/search/was/collectionInfo.properties"));
            else{
                File rootPath = new File("");
                properties.load(new FileReader(rootPath.getAbsolutePath()+"/src/main/resources/collectionInfo.properties"));
            }
        }catch (Exception e){
            throw new NullPointerException("Properties 파일을 찾을 수 없습니다.");
        }
        return properties;
    }

    /**
     * 검색 엔진 파라미터 설정.
     *
     * @param search     검색 객체
     * @param collection 컬렉션 명
     * @param flag       flag 값
     *                     - DEFAULT       : 빈(empty) 파라미터 정의
     *                     - DOCUMENTFIELD : 각 컬렉션 별 출력필드 정의
     *                     - SEARCHFIELD   : 각 컬렉션 별 검색 필드 정의 (파라미터 값이 ALL or null 일 경우)
     * @param properties 프로퍼티
     */
    public void setCollectionInfoValue(Search search, String collection, String flag, Properties properties){
        if(flag.equals("COLLECTION") && search.getCollection().equals("ALL")){
            search.setCollection(properties.getProperty("COLLECTIONS"));
        }else if(flag.equals("DOCUMENTFIELD")){
            search.setDocumentField(properties.getProperty(collection.toUpperCase() + ".DOCUMENTFIELD"));
        }else if(flag.equals("SEARCHFIELD")){
            search.setSearchField(properties.getProperty(collection.toUpperCase() + ".SEARCHFIELD"));
        }else if(flag.equals("CATEGORY")){
            search.setCategoryField(properties.getProperty(collection.toUpperCase() + ".CATEGORY"));
        }
    }

    /**
     * 검색엔진 설정
     *
     * @param wnSearch       검색엔진 설정 전용 객체
     * @param search         검색 객체
     * @param properties     검색엔진 설정 프로퍼티 파일
     * @param collection     컬렉션 명
     * @param hasSearchField 검색필드 (전체,빈값 / 특정 검색필드) 구분
     */
    public void setSearchEngine(QueryAPI530.Search wnSearch, Search search, Properties properties, String collection, Boolean hasSearchField){
        int ret = 0;

        ret = wnSearch.w3SetCodePage("UTF-8");
        ret = wnSearch.w3SetCommonQuery(search.getQuery(),0);
        ret = wnSearch.w3ConnectServer(properties.getProperty("SEARCH_IP"), Integer.parseInt(properties.getProperty("SEARCH_PORT")), Integer.parseInt(properties.getProperty("SEARCH_TIMEOUT")));
        ret = wnSearch.w3AddCollection(collection);
        ret = wnSearch.w3SetPageInfo(collection, search.getStartCount(), search.getListCount());
        ret = wnSearch.w3SetDateRange(collection, search.getStartDate(), search.getEndDate());
        ret = wnSearch.w3SetSpellCorrectionQuery(search.getQuery(),1);
        //각 컬렉션 별 출력필드 호출 및 설정
        setCollectionInfoValue(search, collection, "DOCUMENTFIELD", properties);
        ret = wnSearch.w3SetDocumentField(collection, search.getDocumentField());
        //각 컬렉션 별 검색필드 호출 및 설정
        if(!hasSearchField) setCollectionInfoValue(search, collection, "SEARCHFIELD", properties);
        ret = wnSearch.w3SetSearchField(collection, search.getSearchField());
        //prefix query
        String exquery = setExquery(search);
        if(!exquery.equals("")) ret = wnSearch.w3SetPrefixQuery(collection,exquery,1);
        ret = wnSearch.w3SetFilterQuery(collection,"<sellPrice:gt:"+search.getMinSellPrice()+"> <sellPrice:lt:"+search.getMaxSellPrice()+">");
        //CATEGORY
        setCollectionInfoValue(search, collection, "CATEGORY", properties);
        //카테고리 없을 경우 조건?
        String[] categories = search.getCategoryField().split("#");
        for (String category : categories) {
            ret = wnSearch.w3AddCategoryGroupBy(collection, category.split("\\|")[0], category.split("\\|")[1]);
        }
        String categoryId = search.getCategoryId();
        if(!categoryId.equals("")) ret = wnSearch.w3AddCategoryQuery(collection, "categoryId", categoryId);

        ret = wnSearch.w3SetSortField(collection, search.getSort());
        ret = wnSearch.w3SetQueryAnalyzer(collection, 1, 1, 1, 1 );
        ret = wnSearch.w3ReceiveSearchQueryResult(3 );
    }

    public String setExquery(Search search){
        String exquery = "";

        String serviceCode = search.getServiceCode();
        if(!serviceCode.equals("")) exquery += mkExqueryString(serviceCode, "serviceCode");

        String tag = search.getTag();
        if(!tag.equals("")) exquery += mkExqueryString(tag, "tag");

        String discountTag = search.getDiscountTag();
        if(!discountTag.equals("")) exquery += mkExqueryString(discountTag, "discountTag");

        String serviceTag = search.getServiceTag();
        if(!serviceTag.equals("")) exquery += mkExqueryString(serviceTag, "serviceTag");

        String wine25ItemKindName = search.getWine25ItemKindName();
        if(!wine25ItemKindName.equals("")) exquery += mkExqueryString(wine25ItemKindName,"wine25ItemKindName");

        String wine25RegionSpName = search.getWine25RegionSpName();
        if(!wine25RegionSpName.equals("")) exquery += mkExqueryString(wine25RegionSpName, "wine25RegionSpName");

        String cardDiscountName = search.getCardDiscountName();
        if(!cardDiscountName.equals("")) exquery += mkExqueryString(cardDiscountName,"cardDiscountName");

        String cardDiscountYn = search.getCardDiscountYn();
        if(!cardDiscountYn.equals("")) exquery += mkExqueryString(cardDiscountYn, "cardDiscountYn");

        String adultYn = search.getAdultYn();
        if(!adultYn.equals("")) exquery += mkExqueryString(adultYn, "adultYn");

        String soldOutSp = search.getSoldOutSp();
        if(!soldOutSp.equals("")) exquery += mkExqueryString(soldOutSp, "soldOutSp");

        String deliverySp = search.getDeliverySp();
        if(!deliverySp.equals("")) exquery += mkExqueryString(deliverySp, "deliverySp");

        String stockCheckYn = search.getStockCheckYn();
        if(!stockCheckYn.equals("")) exquery += mkExqueryString(stockCheckYn, "stockCheckYn");

        String recommendItemYn = search.getRecommendItemYn();
        if(!recommendItemYn.equals("")) exquery += mkExqueryString(recommendItemYn, "recommendItemYn");

        return exquery;
    }

    public String mkExqueryString(String parameter, String prefixFieldName){
        String exquery = "";

        String[] parameterArrays = parameter.split(",");
        exquery += "(";
        for(int i=0; i< parameterArrays.length; i++){
            if(i == parameterArrays.length-1) exquery += "<"+prefixFieldName+":contains:"+parameterArrays[i]+">";
            else exquery += "<"+prefixFieldName+":contains:"+parameterArrays[i]+"> | ";
        }
        exquery += ") ";

        return exquery;
    }
}
