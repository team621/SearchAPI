/*
 * Copyright (c) GS Retail.
 * All rights reserved.
 * 모든 권한은 GS리테일에 있으며,
 * GS리테일의 허락없이 소스 및 이진형식을 재배포, 사용하는 행위를 금지합니다.
 */

package com.gsretail.gst.b2e.search.search.model;

import lombok.*;
import java.util.Date;
import org.json.simple.JSONObject;
import java.text.SimpleDateFormat;

/**
 * com.gsretail.gst.b2e.search.search
 * Search
 *
 * @author : WISENUT
 * @date : 2022-06-22
 * @tags : 검색 객체
 */
@Data
@Getter
@Setter
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
    private String StockSupermarketItemCode;
    private String wdlvyItemSpCd;
    private String optItemIncldYn;
    /* 와인 */
    private String wine25GradeCode;
    private String wine25GradeSpCode;
    private String wine25groupPurchaseTypeCode;
    private String wine25BodyFillingSpCode;
    private String wine25SugarContentSpCode;
    private String wine25BreedSpCode;
    private String wine25RegionSpCode;
    private String wine25ItemKindCode;
    private String groupPurchaseYn;
    private String token;
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
        this.prmByQty = nullCheckJsonParameter(jsonObj , "prmByQty" , "");
        this.prmGtQty = nullCheckJsonParameter(jsonObj , "prmGtQty" , "");
        this.StockSupermarketItemCode = nullCheckJsonParameter(jsonObj,"StockSupermarketItemCode","");
        this.wdlvyItemSpCd = nullCheckJsonParameter(jsonObj, "wdlvyItemSpCd", "");
        this.optItemIncldYn = nullCheckJsonParameter(jsonObj, "optItemIncldYn", "");
        /* 와인 */
        this.wine25GradeCode = nullCheckJsonParameter(jsonObj, "wine25GradeCode", "");
        this.wine25GradeSpCode = nullCheckJsonParameter(jsonObj, "wine25GradeSpCode", "");
        this.wine25groupPurchaseTypeCode = nullCheckJsonParameter(jsonObj,"wine25groupPurchaseTypeCode", "");
        this.wine25BodyFillingSpCode = nullCheckJsonParameter(jsonObj, "wine25BodyFillingSpCode","");
        this.wine25SugarContentSpCode = nullCheckJsonParameter(jsonObj, "wine25SugarContentSpCode", "");
        this.wine25BreedSpCode = nullCheckJsonParameter(jsonObj, "wine25BreedSpCode","");
        this.wine25RegionSpCode = nullCheckJsonParameter(jsonObj, "wine25RegionSpCode", "");
        this.wine25ItemKindCode = nullCheckJsonParameter(jsonObj, "wine25ItemKindCode", "");
        this.groupPurchaseYn = nullCheckJsonParameter(jsonObj, "groupPurchaseYn", "");
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
