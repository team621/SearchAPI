/*
 * Copyright (c) GS Retail.
 * All rights reserved.
 * 모든 권한은 GS리테일에 있으며,
 * GS리테일의 허락없이 소스 및 이진형식을 재배포, 사용하는 행위를 금지합니다.
 */

package com.gsretail.gst.b2e.search.search.model;

import org.json.simple.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * com.gsretail.gst.b2e.search.search
 * Search
 *
 * @author : WISENUT
 * @date : 2022-05-16
 * @tags :
 */
public class Search {

    private String query;
    private String collection;
    private String sort;
    private int startCount;
    private int listCount;
    private String documentField;
    private String searchField;
    private String startDate;
    private String endDate;
    private String tag;
    private String discountTag;
    private String soldOutSp;
    private String stockCheckYn;
    private String maxSellPrice;
    private String minSellPrice;
    private String categoryId;
    private String cardDiscountYn;
    private String adultYn;
    private String typoSearch;
    private String recommendItemYn;

    public Search() {}

    public Search(JSONObject jsonObj) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date today = new Date();
        String todayStr = dateFormat.format(today);

        this.query = nullCheckJsonParameter(jsonObj, "query", "");
        this.collection = nullCheckJsonParameter(jsonObj, "collection", "ALL");
        this.sort = nullCheckJsonParameter(jsonObj, "sort", "RANK/DESC");
        this.startCount = Integer.parseInt(nullCheckJsonParameter(jsonObj, "startCount", "0"));
        this.listCount = Integer.parseInt(nullCheckJsonParameter(jsonObj, "listCount", "3"));
        this.documentField = "";
        this.searchField = nullCheckJsonParameter(jsonObj, "searchField", "ALL");
        this.startDate = nullCheckJsonParameter(jsonObj, "startDate", "1970/01/01");
        this.endDate = nullCheckJsonParameter(jsonObj, "endDate", todayStr);
        this.tag = nullCheckJsonParameter(jsonObj, "tag", "");
        this.discountTag = nullCheckJsonParameter(jsonObj, "discountTag", "");
        this.soldOutSp = nullCheckJsonParameter(jsonObj, "soldOutSeparateCode", "");
        this.stockCheckYn = nullCheckJsonParameter(jsonObj, "stockCheckYn","");
        this.maxSellPrice = nullCheckJsonParameter(jsonObj, "maxSellPrice", "9999999");
        this.minSellPrice = nullCheckJsonParameter(jsonObj, "minSellPrice", "0");
        this.categoryId = nullCheckJsonParameter(jsonObj, "categoryId", "");
        this.cardDiscountYn = nullCheckJsonParameter(jsonObj, "cardDiscountYn", "");
        this.adultYn = nullCheckJsonParameter(jsonObj, "adultYn", "");
        this.typoSearch = nullCheckJsonParameter(jsonObj, "typoSearch", "N");
        this.recommendItemYn = nullCheckJsonParameter(jsonObj, "recommendItemYn", "");
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getStartCount() {
        return startCount;
    }

    public void setStartCount(int startCount) {
        this.startCount = startCount;
    }

    public int getListCount() {
        return listCount;
    }

    public void setListCount(int listCount) {
        this.listCount = listCount;
    }

    public String getDocumentField() {
        return documentField;
    }

    public void setDocumentField(String documentField) {
        this.documentField = documentField;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDiscountTag() {
        return discountTag;
    }

    public void setDiscountTag(String discountTag) {
        this.discountTag = discountTag;
    }

    public String getSoldOutSp() {
        return soldOutSp;
    }

    public void setSoldOutSp(String soldOutSp) {
        this.soldOutSp = soldOutSp;
    }

    public String getStockCheckYn() {
        return stockCheckYn;
    }

    public void setStockCheckYn(String stockCheckYn) {
        this.stockCheckYn = stockCheckYn;
    }

    public String getMaxSellPrice() {
        return maxSellPrice;
    }

    public void setMaxSellPrice(String maxSellPrice) {
        this.maxSellPrice = maxSellPrice;
    }

    public String getMinSellPrice() {
        return minSellPrice;
    }

    public void setMinSellPrice(String minSellPrice) {
        this.minSellPrice = minSellPrice;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCardDiscountYn() { return cardDiscountYn; }

    public void setCardDiscountYn(String cardDiscountYn) { this.cardDiscountYn = cardDiscountYn; }

    public String getAdultYn() { return adultYn; }

    public void setAdultYn(String adultYn) { this.adultYn = adultYn; }

    public String getTypoSearch() { return typoSearch; }

    public void setTypoSearch(String typoSearch) { this.typoSearch = typoSearch; }

    public String getRecommendItemYn() {
        return recommendItemYn;
    }

    public void setRecommendItemYn(String recommendItemYn) {
        this.recommendItemYn = recommendItemYn;
    }

    /**
     * get arrays
     *
     * @param value the value
     * @return the string [ ]
     */
    public String[] getArrays(String value){
        String[] arrays = value.split(",");
        return arrays;
    }

    /**
     * Null check json parameter string.
     *
     * @param jsonObj       the json obj
     * @param parameterName the parameter name
     * @param defaultValue  the default value
     * @return the string
     */
    public String nullCheckJsonParameter(JSONObject jsonObj, String parameterName, String defaultValue){
        String parameterResult = "";

        parameterResult = jsonObj.get(parameterName) == null ? defaultValue : (String)jsonObj.get(parameterName);
        if(parameterResult.equals("")) parameterResult = defaultValue;

        return parameterResult;
    }
}
