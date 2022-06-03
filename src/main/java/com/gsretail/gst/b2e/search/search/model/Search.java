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
    private int storeCount;
    private String documentField;
    private String searchField;
    private String startDate;
    private String endDate;
    private String serviceCode;
    private String tag;
    private String discountTag;
    private String serviceTag;
    private String wine25ItemKindName;
    private String wine25RegionSpName;
    private String itemCode;
    private String cardDiscountName;
    private String cardDiscountYn;
    private String adultYn;
    private String soldOutSp;
    private String deliverySp;
    private String stockCheckYn;
    private String recommendItemYn;
    private String maxSellPrice;
    private String minSellPrice;
    private String categoryId;
    private String typoSearch;
    private String categoryField;
    private String prmByQty;
    private String prmGtQty;
    private String storeCode;
    private String supermarketItemCode;

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
        this.serviceCode = nullCheckJsonParameter(jsonObj, "serviceCode", "");
        this.tag = nullCheckJsonParameter(jsonObj, "tag", "");
        this.discountTag = nullCheckJsonParameter(jsonObj, "discountTag", "");
        this.serviceTag = nullCheckJsonParameter(jsonObj, "serviceTag", "");
        this.wine25ItemKindName = nullCheckJsonParameter(jsonObj, "wine25ItemKindName", "");
        this.wine25RegionSpName = nullCheckJsonParameter(jsonObj, "wine25RegionSpName", "");
        this.itemCode = nullCheckJsonParameter(jsonObj, "itemCode", "");
        this.cardDiscountName = nullCheckJsonParameter(jsonObj, "cardDiscountName", "");
        this.cardDiscountYn = nullCheckJsonParameter(jsonObj, "cardDiscountYn", "");
        this.adultYn = nullCheckJsonParameter(jsonObj, "adultYn", "");
        this.soldOutSp = nullCheckJsonParameter(jsonObj, "soldOutSp", "");
        this.deliverySp = nullCheckJsonParameter(jsonObj, "deliverySp", "");
        this.stockCheckYn = nullCheckJsonParameter(jsonObj, "stockCheckYn","");
        this.recommendItemYn = nullCheckJsonParameter(jsonObj, "recommendItemYn", "");
        this.maxSellPrice = nullCheckJsonParameter(jsonObj, "maxSellPrice", "9999999");
        this.minSellPrice = nullCheckJsonParameter(jsonObj, "minSellPrice", "0");
        this.categoryId = nullCheckJsonParameter(jsonObj, "categoryId", "");
        this.typoSearch = nullCheckJsonParameter(jsonObj, "typoSearch", "N");
        this.storeCode = nullCheckJsonParameter(jsonObj , "storeCode" , "");
        this.supermarketItemCode = nullCheckJsonParameter(jsonObj , "supermarketItemCode" , "");
        this.storeCount = Integer.parseInt(nullCheckJsonParameter(jsonObj, "storeCount", "3"));
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

    public int getStoreCount() {
        return storeCount;
    }

    public void setStoreCount(int storeCount) {
        this.storeCount = storeCount;
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

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
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

    public String getServiceTag() {
        return serviceTag;
    }

    public void setServiceTag(String serviceTag) {
        this.serviceTag = serviceTag;
    }

    public String getWine25ItemKindName() {
        return wine25ItemKindName;
    }

    public void setWine25ItemKindName(String wine25ItemKindName) {
        this.wine25ItemKindName = wine25ItemKindName;
    }

    public String getWine25RegionSpName() {
        return wine25RegionSpName;
    }

    public void setWine25RegionSpName(String wine25RegionSpName) {
        this.wine25RegionSpName = wine25RegionSpName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getCardDiscountName() {
        return cardDiscountName;
    }

    public void setCardDiscountName(String cardDiscountName) {
        this.cardDiscountName = cardDiscountName;
    }

    public String getCardDiscountYn() {
        return cardDiscountYn;
    }

    public void setCardDiscountYn(String cardDiscountYn) {
        this.cardDiscountYn = cardDiscountYn;
    }

    public String getAdultYn() {
        return adultYn;
    }

    public void setAdultYn(String adultYn) {
        this.adultYn = adultYn;
    }

    public String getSoldOutSp() {
        return soldOutSp;
    }

    public void setSoldOutSp(String soldOutSp) {
        this.soldOutSp = soldOutSp;
    }

    public String getDeliverySp() {
        return deliverySp;
    }

    public void setDeliverySp(String deliverySp) {
        this.deliverySp = deliverySp;
    }

    public String getStockCheckYn() {
        return stockCheckYn;
    }

    public void setStockCheckYn(String stockCheckYn) {
        this.stockCheckYn = stockCheckYn;
    }

    public String getRecommendItemYn() {
        return recommendItemYn;
    }

    public void setRecommendItemYn(String recommendItemYn) {
        this.recommendItemYn = recommendItemYn;
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

    public String getTypoSearch() {
        return typoSearch;
    }

    public void setTypoSearch(String typoSearch) {
        this.typoSearch = typoSearch;
    }

    public String getCategoryField() {
        return categoryField;
    }

    public void setCategoryField(String categoryField) {
        this.categoryField = categoryField;
    }

    public void setPrmByQty(String prmByQty) {
        this.prmByQty = prmByQty;
    }

    public String getPrmGtQty() {
        return prmGtQty;
    }

    public void setPrmGtQty(String prmGtQty) {
        this.prmGtQty = prmGtQty;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getSupermarketItemCode() {
        return supermarketItemCode;
    }

    public void setSupermarketItemCode(String supermarketItemCode) {
        this.supermarketItemCode = supermarketItemCode;
    }

    public String getPrmByQty() {
        return prmByQty;
    }
}
