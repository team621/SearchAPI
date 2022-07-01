package com.gsretail.gst.b2e.search.search.common;

import java.util.Properties;

import static java.lang.System.getProperties;

/**
 * packageName    : com.gsretail.gst.b2e.search.search.common
 * fileName       : WNCollection
 * author         : USER
 * date           : 2022-06-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-07        USER       최초 생성
 */
public class WNCollection {
    static String SEARCH_IP="27.122.138.23";
    static int SEARCH_PORT=5052;
    //운영
    public String[] COLLECTIONS = new String[]{"oneplus","thefresh","woodel_gs","woodel_gs_pick","woodel_mart","woodel_mart_pick","gs25_reservation","wine25_gs","giftcard","exhibition","store"};
    public String[] COLLECTIONS_NAME = new String[]{"oneplus","thefresh","woodel_gs","woodel_gs_pick","woodel_mart","woodel_mart_pick","gs25_reservation","wine25_gs","giftcard","exhibition","store"};

    public String[] MERGE_COLLECTIONS = new String[]{"mergeCollection"};

    public String[][] MERGE_COLLECTION_INFO = new String[][]
            {
                    {
                            "mergeCollection", // set merge collection name
                            "oneplus/thefresh/woodel_gs/woodel_gs_pick/woodel_mart/gs25_reservation/wine25_gs/giftcard", // set collection name, delimiter: /
                            "0,3",  // set merge collection pageinfo (start,count)
                            "itemImageUrl,itemName,sellPrice,discountPrice,itemCode,supermarketItemCode,ALIAS",// set merge document field
                            "itemImageUrl,itemName,sellPrice,discountPrice,itemCode,supermarketItemCode,ALIAS/itemImageUrl,itemName,sellPrice,discountPrice,itemCode,supermarketItemCode,ALIAS/itemImageUrl,itemName,sellPrice,discountPrice,itemCode,supermarketItemCode,ALIAS/itemImageUrl,itemName,sellPrice,discountPrice,itemCode,supermarketItemCode,ALIAS/itemImageUrl,itemName,sellPrice,discountPrice,itemCode,supermarketItemCode,ALIAS/itemImageUrl,itemName,sellPrice,discountPrice,itemCode,supermarketItemCode,ALIAS/itemImageUrl,itemName,sellPrice,discountPrice,itemCode,supermarketItemCode,ALIAS/itemImageUrl,itemName,sellPrice,discountPrice,itemCode,supermarketItemCode,ALIAS", // set document field, delimiter: /
                            "", // set merge collection multi-group-by field
                            "", // set merge collection multi-group-by field, delimiter: /
                            "", // set merge collection category-group-by field
                            ""  // set collection multi-group-by field, delimiter: /
                    }
            };

    public String[][] COLLECTION_INFO = new String[][]{
            {
                    "oneplus", // set index name
                    "oneplus", // set collection name
                    "0,3",  // set pageinfo (start,count)
                    "1,1,1,1,0", // set query analyzer (useKMA,isCase,useOriginal,useSynonym, duplcated detection)
                    "RANK/DESC",  // set sort field (field,order) multi sort '/'
                    "basic,rpfmo,100",  // set sort field (field,order) multi sort '/'
                    "itemName,shortItemName,storeName",// set search field
                    "DOCID,itemImageUrl,serviceCode,tag,discountTag,serviceTag,wine25ItemKindName,wine25RegionSpName,itemCode,itemName,shortItemName,sellPrice,discountPrice,discountRate,discountAmount,cardDiscountName,cardDiscountYn,extraPresentItemName,extraPresentItemShortName,extraPresentImageUrl,adultYn,pickupDate,stockCount,buyQuantity,soldOutSp,deliverySp,starPoint,prmByQty,prmGtQty,stockCheckYn,exposureSeq,categoryId,categoryName,recommendItemYn,storeName,storeCode,pickupStartDate,pickupEndDate,prtnitemcd,supermarketItemCode,ALIAS",// set document field
                    "", // set date range
                    "", // set rank range
                    "", // set prefix query, example: <fieldname:contains:value1>|<fieldname:contains:value2>/1,  (fieldname:contains:value) and ' ', or '|', not '!' / operator (AND:1, OR:0)
                    "", // set collection query (<fieldname:contains:value^weight | value^weight>/option...) and ' ', or '|'
                    "", // set boost query (<fieldname:contains:value> | <field3:contains:value>...) and ' ', or '|'
                    "", // set filter operation (<fieldname:operator:value>)
                    "", // set groupby field(field, count)
                    "", // set sort field group(field/order,field/order,...)
                    "", // set categoryBoost(fieldname,matchType,boostID,boostKeyword)
                    "categoryId:1/SC|categoryName:1/SC", // set categoryGroupBy (fieldname:value)
                    "", // set categoryQuery (fieldname:value)
                    "", // set property group (fieldname,min,max, groupcount)
                    "serviceCode,tag,discountTag,serviceTag,wine25ItemKindName,wine25RegionSpName,itemCode,cardDiscountName,cardDiscountYn,adultYn,soldOutSp,deliverySp,stockCheckYn,recommendItemYn,storeCode,prmByQty,prmGtQty,supermarketItemCode", // use check prefix query filed
                    "itemCode,sellPrice,discountPrice,discountRate,pickupDate,stockCount,starPoint,exposureSeq,pickupStartDate,pickupEndDate", // set use check fast access field
                    "", // set multigroupby field
                    "", // set auth query (Auth Target Field, Auth Collection, Auth Reference Field, Authority Query)
                    "", // set Duplicate Detection Criterion Field, RANK/DESC,DATE/DESC
                    "oneplus" // collection display name
            }
            ,
            {
                    "thefresh", // set index name
                    "thefresh", // set collection name
                    "0,3",  // set pageinfo (start,count)
                    "1,1,1,1,0", // set query analyzer (useKMA,isCase,useOriginal,useSynonym, duplcated detection)
                    "RANK/DESC",  // set sort field (field,order) multi sort '/'
                    "basic,rpfmo,100",  // set sort field (field,order) multi sort '/'
                    "itemName,shortItemName,storeName",// set search field
                    "DOCID,itemImageUrl,serviceCode,tag,discountTag,serviceTag,wine25ItemKindName,wine25RegionSpName,itemCode,itemName,shortItemName,sellPrice,discountPrice,discountRate,discountAmount,cardDiscountName,cardDiscountYn,extraPresentItemName,extraPresentItemShortName,extraPresentImageUrl,adultYn,pickupDate,stockCount,buyQuantity,soldOutSp,deliverySp,starPoint,prmByQty,prmGtQty,stockCheckYn,exposureSeq,categoryId,categoryName,recommendItemYn,storeName,storeCode,pickupStartDate,pickupEndDate,prtnitemcd,tagDate,supermarketItemCode,ALIAS",// set document field
                    "", // set date range
                    "", // set rank range
                    "", // set prefix query, example: <fieldname:contains:value1>|<fieldname:contains:value2>/1,  (fieldname:contains:value) and ' ', or '|', not '!' / operator (AND:1, OR:0)
                    "", // set collection query (<fieldname:contains:value^weight | value^weight>/option...) and ' ', or '|'
                    "", // set boost query (<fieldname:contains:value> | <field3:contains:value>...) and ' ', or '|'
                    "", // set filter operation (<fieldname:operator:value>)
                    "supermarketItemCode,1", // set groupby field(field, count)
                    "", // set sort field group(field/order,field/order,...)
                    "", // set categoryBoost(fieldname,matchType,boostID,boostKeyword)
                    "categoryId:1,2/SC|categoryName:1,2/SC", // set categoryGroupBy (fieldname:value)
                    "", // set categoryQuery (fieldname:value)
                    "", // set property group (fieldname,min,max, groupcount)
                    "serviceCode,tag,discountTag,serviceTag,wine25ItemKindName,wine25RegionSpName,itemCode,cardDiscountName,cardDiscountYn,adultYn,soldOutSp,deliverySp,stockCheckYn,recommendItemYn,storeCode,prmByQty,prmGtQty,supermarketItemCode", // use check prefix query filed
                    "itemCode,sellPrice,discountPrice,discountRate,pickupDate,stockCount,starPoint,exposureSeq,storeCode,pickupStartDate,pickupEndDate,tagDate,supermarketItemCode", // set use check fast access field
                    "", // set multigroupby field
                    "", // set auth query (Auth Target Field, Auth Collection, Auth Reference Field, Authority Query)
                    "", // set Duplicate Detection Criterion Field, RANK/DESC,DATE/DESC
                    "thefresh" // collection display name
            }
            ,
            {
                    "woodel_gs", // set index name
                    "woodel_gs", // set collection name
                    "0,3",  // set pageinfo (start,count)
                    "1,1,1,1,0", // set query analyzer (useKMA,isCase,useOriginal,useSynonym, duplcated detection)
                    "RANK/DESC",  // set sort field (field,order) multi sort '/'
                    "basic,rpfmo,100",  // set sort field (field,order) multi sort '/'
                    "itemName,shortItemName,storeName",// set search field
                    "DOCID,itemImageUrl,serviceCode,tag,discountTag,serviceTag,wine25ItemKindName,wine25RegionSpName,itemCode,itemName,shortItemName,sellPrice,discountPrice,discountRate,discountAmount,cardDiscountName,cardDiscountYn,extraPresentItemName,extraPresentItemShortName,extraPresentImageUrl,adultYn,pickupDate,stockCount,buyQuantity,soldOutSp,deliverySp,starPoint,prmByQty,prmGtQty,stockCheckYn,exposureSeq,categoryId,categoryName,recommendItemYn,storeName,storeCode,pickupStartDate,pickupEndDate,wdlvyItemSpCd,optItemIncldYn,supermarketItemCode,ALIAS",// set document field
                    "", // set date range
                    "", // set rank range
                    "", // set prefix query, example: <fieldname:contains:value1>|<fieldname:contains:value2>/1,  (fieldname:contains:value) and ' ', or '|', not '!' / operator (AND:1, OR:0)
                    "", // set collection query (<fieldname:contains:value^weight | value^weight>/option...) and ' ', or '|'
                    "", // set boost query (<fieldname:contains:value> | <field3:contains:value>...) and ' ', or '|'
                    "", // set filter operation (<fieldname:operator:value>)
                    "supermarketItemCode,1", // set groupby field(field, count)
                    "", // set sort field group(field/order,field/order,...)
                    "", // set categoryBoost(fieldname,matchType,boostID,boostKeyword)
                    "categoryId:1/SC|categoryName:1/SC", // set categoryGroupBy (fieldname:value)
                    "", // set categoryQuery (fieldname:value)
                    "", // set property group (fieldname,min,max, groupcount)
                    "serviceCode,tag,discountTag,serviceTag,wine25ItemKindName,wine25RegionSpName,itemCode,cardDiscountName,cardDiscountYn,adultYn,soldOutSp,deliverySp,stockCheckYn,recommendItemYn,storeCode,prmByQty,prmGtQty,wdlvyItemSpCd,optItemIncldYn,supermarketItemCode", // use check prefix query filed
                    "itemCode,sellPrice,discountPrice,discountRate,pickupDate,stockCount,starPoint,exposureSeq,pickupStartDate,pickupEndDate,supermarketItemCode", // set use check fast access field
                    "", // set multigroupby field
                    "", // set auth query (Auth Target Field, Auth Collection, Auth Reference Field, Authority Query)
                    "", // set Duplicate Detection Criterion Field, RANK/DESC,DATE/DESC
                    "woodel_gs" // collection display name
            }
            ,
            {
                    "woodel_gs_pick", // set index name
                    "woodel_gs_pick", // set collection name
                    "0,3",  // set pageinfo (start,count)
                    "1,1,1,1,0", // set query analyzer (useKMA,isCase,useOriginal,useSynonym, duplcated detection)
                    "RANK/DESC",  // set sort field (field,order) multi sort '/'
                    "basic,rpfmo,100",  // set sort field (field,order) multi sort '/'
                    "itemName,shortItemName,storeName",// set search field
                    "DOCID,itemImageUrl,serviceCode,tag,discountTag,serviceTag,wine25ItemKindName,wine25RegionSpName,itemCode,itemName,shortItemName,sellPrice,discountPrice,discountRate,discountAmount,cardDiscountName,cardDiscountYn,extraPresentItemName,extraPresentItemShortName,extraPresentImageUrl,adultYn,pickupDate,stockCount,buyQuantity,soldOutSp,deliverySp,starPoint,prmByQty,prmGtQty,stockCheckYn,exposureSeq,categoryId,categoryName,recommendItemYn,storeName,storeCode,pickupStartDate,pickupEndDate,wdlvyItemSpCd,optItemIncldYn,supermarketItemCode,ALIAS",// set document field
                    "", // set date range
                    "", // set rank range
                    "", // set prefix query, example: <fieldname:contains:value1>|<fieldname:contains:value2>/1,  (fieldname:contains:value) and ' ', or '|', not '!' / operator (AND:1, OR:0)
                    "", // set collection query (<fieldname:contains:value^weight | value^weight>/option...) and ' ', or '|'
                    "", // set boost query (<fieldname:contains:value> | <field3:contains:value>...) and ' ', or '|'
                    "", // set filter operation (<fieldname:operator:value>)
                    "supermarketItemCode,1", // set groupby field(field, count)
                    "", // set sort field group(field/order,field/order,...)
                    "", // set categoryBoost(fieldname,matchType,boostID,boostKeyword)
                    "categoryId:1/SC|categoryName:1/SC", // set categoryGroupBy (fieldname:value)
                    "", // set categoryQuery (fieldname:value)
                    "", // set property group (fieldname,min,max, groupcount)
                    "serviceCode,tag,discountTag,serviceTag,wine25ItemKindName,wine25RegionSpName,itemCode,cardDiscountName,cardDiscountYn,adultYn,soldOutSp,deliverySp,stockCheckYn,recommendItemYn,storeCode,prmByQty,prmGtQty,wdlvyItemSpCd,optItemIncldYn,supermarketItemCode", // use check prefix query filed
                    "itemCode,sellPrice,discountPrice,discountRate,pickupDate,stockCount,starPoint,exposureSeq,pickupStartDate,pickupEndDate,supermarketItemCode", // set use check fast access field
                    "", // set multigroupby field
                    "", // set auth query (Auth Target Field, Auth Collection, Auth Reference Field, Authority Query)
                    "", // set Duplicate Detection Criterion Field, RANK/DESC,DATE/DESC
                    "woodel_gs_pick" // collection display name
            }
            ,
            {
                    "woodel_mart", // set index name
                    "woodel_mart", // set collection name
                    "0,3",  // set pageinfo (start,count)
                    "1,1,1,1,0", // set query analyzer (useKMA,isCase,useOriginal,useSynonym, duplcated detection)
                    "RANK/DESC,DATE/DESC",  // set sort field (field,order) multi sort '/'
                    "basic,rpfmo,100",  // set sort field (field,order) multi sort '/'
                    "itemName,shortItemName,storeName",// set search field
                    "DOCID,itemImageUrl,serviceCode,tag,discountTag,serviceTag,wine25ItemKindName,wine25RegionSpName,itemCode,itemName,shortItemName,sellPrice,discountPrice,discountRate,discountAmount,cardDiscountName,cardDiscountYn,extraPresentItemName,extraPresentItemShortName,extraPresentImageUrl,adultYn,pickupDate,stockCount,buyQuantity,soldOutSp,deliverySp,starPoint,prmByQty,prmGtQty,stockCheckYn,exposureSeq,categoryId,categoryName,recommendItemYn,storeName,storeCode,pickupStartDate,pickupEndDate,wdlvyItemSpCd,optItemIncldYn,supermarketItemCode,evntKindCd,ALIAS",// set document field
                    "", // set date range
                    "", // set rank range
                    "", // set prefix query, example: <fieldname:contains:value1>|<fieldname:contains:value2>/1,  (fieldname:contains:value) and ' ', or '|', not '!' / operator (AND:1, OR:0)
                    "", // set collection query (<fieldname:contains:value^weight | value^weight>/option...) and ' ', or '|'
                    "", // set boost query (<fieldname:contains:value> | <field3:contains:value>...) and ' ', or '|'
                    "", // set filter operation (<fieldname:operator:value>)
                    "supermarketItemCode,1", // set groupby field(field, count)
                    "", // set sort field group(field/order,field/order,...)
                    "", // set categoryBoost(fieldname,matchType,boostID,boostKeyword)
                    "categoryId:1/SC|categoryName:1/SC", // set categoryGroupBy (fieldname:value)
                    "", // set categoryQuery (fieldname:value)
                    "", // set property group (fieldname,min,max, groupcount)
                    "serviceCode,tag,discountTag,serviceTag,wine25ItemKindName,wine25RegionSpName,itemCode,cardDiscountName,cardDiscountYn,adultYn,soldOutSp,deliverySp,stockCheckYn,recommendItemYn,storeCode,prmByQty,prmGtQty,wdlvyItemSpCd,optItemIncldYn,supermarketItemCode,evntKindCd", // use check prefix query filed
                    "itemCode,sellPrice,discountPrice,discountRate,pickupDate,stockCount,starPoint,exposureSeq,storeCode,pickupStartDate,pickupEndDate,supermarketItemCode", // set use check fast access field
                    "", // set multigroupby field
                    "", // set auth query (Auth Target Field, Auth Collection, Auth Reference Field, Authority Query)
                    "", // set Duplicate Detection Criterion Field, RANK/DESC,DATE/DESC
                    "woodel_mart" // collection display name
            }
            ,
            {
                    "woodel_mart_pick", // set index name
                    "woodel_mart_pick", // set collection name
                    "0,3",  // set pageinfo (start,count)
                    "1,1,1,1,0", // set query analyzer (useKMA,isCase,useOriginal,useSynonym, duplcated detection)
                    "RANK/DESC,DATE/DESC",  // set sort field (field,order) multi sort '/'
                    "basic,rpfmo,100",  // set sort field (field,order) multi sort '/'
                    "itemName,shortItemName,storeName",// set search field
                    "DOCID,itemImageUrl,serviceCode,tag,discountTag,serviceTag,wine25ItemKindName,wine25RegionSpName,itemCode,itemName,shortItemName,sellPrice,discountPrice,discountRate,discountAmount,cardDiscountName,cardDiscountYn,extraPresentItemName,extraPresentItemShortName,extraPresentImageUrl,adultYn,pickupDate,stockCount,buyQuantity,soldOutSp,deliverySp,starPoint,prmByQty,prmGtQty,stockCheckYn,exposureSeq,categoryId,categoryName,recommendItemYn,storeName,storeCode,pickupStartDate,pickupEndDate,wdlvyItemSpCd,optItemIncldYn,supermarketItemCode,evntKindCd,ALIAS",// set document field
                    "", // set date range
                    "", // set rank range
                    "", // set prefix query, example: <fieldname:contains:value1>|<fieldname:contains:value2>/1,  (fieldname:contains:value) and ' ', or '|', not '!' / operator (AND:1, OR:0)
                    "", // set collection query (<fieldname:contains:value^weight | value^weight>/option...) and ' ', or '|'
                    "", // set boost query (<fieldname:contains:value> | <field3:contains:value>...) and ' ', or '|'
                    "", // set filter operation (<fieldname:operator:value>)
                    "supermarketItemCode,1", // set groupby field(field, count)
                    "", // set sort field group(field/order,field/order,...)
                    "", // set categoryBoost(fieldname,matchType,boostID,boostKeyword)
                    "categoryId:1/SC|categoryName:1/SC", // set categoryGroupBy (fieldname:value)
                    "", // set categoryQuery (fieldname:value)
                    "", // set property group (fieldname,min,max, groupcount)
                    "serviceCode,tag,discountTag,serviceTag,wine25ItemKindName,wine25RegionSpName,itemCode,cardDiscountName,cardDiscountYn,adultYn,soldOutSp,deliverySp,stockCheckYn,recommendItemYn,storeCode,prmByQty,prmGtQty,wdlvyItemSpCd,optItemIncldYn,supermarketItemCode,evntKindCd", // use check prefix query filed
                    "itemCode,sellPrice,discountPrice,discountRate,pickupDate,stockCount,starPoint,exposureSeq,storeCode,pickupStartDate,pickupEndDate,supermarketItemCode", // set use check fast access field
                    "", // set multigroupby field
                    "", // set auth query (Auth Target Field, Auth Collection, Auth Reference Field, Authority Query)
                    "", // set Duplicate Detection Criterion Field, RANK/DESC,DATE/DESC
                    "woodel_mart_pick" // collection display name
            }
            ,
            {
                    "gs25_reservation", // set index name
                    "gs25_reservation", // set collection name
                    "0,3",  // set pageinfo (start,count)
                    "1,1,1,1,0", // set query analyzer (useKMA,isCase,useOriginal,useSynonym, duplcated detection)
                    "RANK/DESC,DATE/DESC",  // set sort field (field,order) multi sort '/'
                    "basic,rpfmo,100",  // set sort field (field,order) multi sort '/'
                    "itemName,shortItemName,storeName",// set search field
                    "DOCID,itemImageUrl,serviceCode,tag,discountTag,serviceTag,wine25ItemKindName,wine25RegionSpName,itemCode,itemName,shortItemName,sellPrice,discountPrice,discountRate,discountAmount,cardDiscountName,cardDiscountYn,extraPresentItemName,extraPresentItemShortName,extraPresentImageUrl,adultYn,pickupDate,stockCount,buyQuantity,soldOutSp,deliverySp,starPoint,prmByQty,prmGtQty,stockCheckYn,exposureSeq,categoryId,categoryName,recommendItemYn,storeName,storeCode,pickupStartDate,pickupEndDate,wdlvyItemSpCd,optItemIncldYn,supermarketItemCode,ALIAS",// set document field
                    "", // set date range
                    "", // set rank range
                    "", // set prefix query, example: <fieldname:contains:value1>|<fieldname:contains:value2>/1,  (fieldname:contains:value) and ' ', or '|', not '!' / operator (AND:1, OR:0)
                    "", // set collection query (<fieldname:contains:value^weight | value^weight>/option...) and ' ', or '|'
                    "", // set boost query (<fieldname:contains:value> | <field3:contains:value>...) and ' ', or '|'
                    "", // set filter operation (<fieldname:operator:value>)
                    "", // set groupby field(field, count)
                    "", // set sort field group(field/order,field/order,...)
                    "", // set categoryBoost(fieldname,matchType,boostID,boostKeyword)
                    "categoryId:1/SC|categoryName:1/SC", // set categoryGroupBy (fieldname:value)
                    "", // set categoryQuery (fieldname:value)
                    "", // set property group (fieldname,min,max, groupcount)
                    "serviceCode,tag,discountTag,serviceTag,wine25ItemKindName,wine25RegionSpName,itemCode,cardDiscountName,cardDiscountYn,adultYn,soldOutSp,deliverySp,stockCheckYn,recommendItemYn,storeCode,prmByQty,prmGtQty,wdlvyItemSpCd,optItemIncldYn,supermarketItemCode", // use check prefix query filed
                    "itemCode,sellPrice,discountPrice,discountRate,pickupDate,stockCount,starPoint,exposureSeq,storeCode,pickupStartDate,pickupEndDate,supermarketItemCode", // set use check fast access field
                    "", // set multigroupby field
                    "", // set auth query (Auth Target Field, Auth Collection, Auth Reference Field, Authority Query)
                    "", // set Duplicate Detection Criterion Field, RANK/DESC,DATE/DESC
                    "gs25_reservation" // collection display name
            }
            ,
            {
                    "wine25_gs", // set index name
                    "wine25_gs", // set collection name
                    "0,3",  // set pageinfo (start,count)
                    "1,1,1,1,0", // set query analyzer (useKMA,isCase,useOriginal,useSynonym, duplcated detection)
                    "RANK/DESC,DATE/DESC",  // set sort field (field,order) multi sort '/'
                    "basic,rpfmo,100",  // set sort field (field,order) multi sort '/'
                    "itemName,shortItemName,storeName",// set search field
                    "DOCID,itemImageUrl,serviceCode,tag,discountTag,serviceTag,wine25ItemKindName,wine25RegionSpName,itemCode,itemName,shortItemName,sellPrice,discountPrice,discountRate,discountAmount,cardDiscountName,cardDiscountYn,extraPresentItemName,extraPresentItemShortName,extraPresentImageUrl,adultYn,stockCount,buyQuantity,soldOutSp,deliverySp,starPoint,prmByQty,prmGtQty,stockCheckYn,exposureSeq,categoryId,categoryName,recommendItemYn,storeName,storeCode,wdlvyItemSpCd,optItemIncldYn,supermarketItemCode,groupPurchaseBeginDttm,groupPurchaseEndDttm,groupPurchaseYn,itemEnglishName,price,wine25GradeCode,wine25GradeName,wine25GradeSpCode,wine25groupPurchaseTypeCode,wine25BodyFillingSpCode,wine25SugarContentSpCode,wine25BreedSpCode,wine25RegionSpCode,wine25ItemKindCode,pickupDate,pickupStartDate,pickupEndDate,ALIAS",// set document field
                    "", // set date range
                    "", // set rank range
                    "", // set prefix query, example: <fieldname:contains:value1>|<fieldname:contains:value2>/1,  (fieldname:contains:value) and ' ', or '|', not '!' / operator (AND:1, OR:0)
                    "", // set collection query (<fieldname:contains:value^weight | value^weight>/option...) and ' ', or '|'
                    "", // set boost query (<fieldname:contains:value> | <field3:contains:value>...) and ' ', or '|'
                    "", // set filter operation (<fieldname:operator:value>)
                    "", // set groupby field(field, count)
                    "", // set sort field group(field/order,field/order,...)
                    "", // set categoryBoost(fieldname,matchType,boostID,boostKeyword)
                    "categoryId:1/SC|categoryName:1/SC", // set categoryGroupBy (fieldname:value)
                    "", // set categoryQuery (fieldname:value)
                    "", // set property group (fieldname,min,max, groupcount)
                    "serviceCode,tag,discountTag,serviceTag,wine25ItemKindName,wine25RegionSpName,itemCode,cardDiscountName,cardDiscountYn,adultYn,soldOutSp,deliverySp,stockCheckYn,recommendItemYn,storeCode,prmByQty,prmGtQty,wdlvyItemSpCd,optItemIncldYn,supermarketItemCode,wine25GradeCode,wine25GradeSpCode,wine25groupPurchaseTypeCode,wine25BodyFillingSpCode,wine25SugarContentSpCode,wine25BreedSpCode,wine25RegionSpCode,wine25ItemKindCode", // use check prefix query filed
                    "itemCode,sellPrice,discountPrice,discountRate,stockCount,starPoint,exposureSeq,storeCode,supermarketItemCode,pickupDate,pickupStartDate,pickupEndDate", // set use check fast access field
                    "", // set multigroupby field
                    "", // set auth query (Auth Target Field, Auth Collection, Auth Reference Field, Authority Query)
                    "", // set Duplicate Detection Criterion Field, RANK/DESC,DATE/DESC
                    "wine25_gs" // collection display name
            }
            ,
            {
                    "giftcard", // set index name
                    "giftcard", // set collection name
                    "0,3",  // set pageinfo (start,count)
                    "1,1,1,1,0", // set query analyzer (useKMA,isCase,useOriginal,useSynonym, duplcated detection)
                    "RANK/DESC,DATE/DESC",  // set sort field (field,order) multi sort '/'
                    "basic,rpfmo,100",  // set sort field (field,order) multi sort '/'
                    "itemName,shortItemName,storeName",// set search field
                    "DOCID,itemImageUrl,serviceCode,tag,discountTag,serviceTag,wine25ItemKindName,wine25RegionSpName,itemCode,itemName,shortItemName,sellPrice,discountPrice,discountRate,discountAmount,cardDiscountName,cardDiscountYn,extraPresentItemName,extraPresentItemShortName,extraPresentImageUrl,adultYn,pickupDate,stockCount,buyQuantity,soldOutSp,deliverySp,starPoint,prmByQty,prmGtQty,stockCheckYn,exposureSeq,categoryId,categoryName,recommendItemYn,storeName,storeCode,pickupStartDate,pickupEndDate,supermarketItemCode,ALIAS",// set document field
                    "", // set date range
                    "", // set rank range
                    "", // set prefix query, example: <fieldname:contains:value1>|<fieldname:contains:value2>/1,  (fieldname:contains:value) and ' ', or '|', not '!' / operator (AND:1, OR:0)
                    "", // set collection query (<fieldname:contains:value^weight | value^weight>/option...) and ' ', or '|'
                    "", // set boost query (<fieldname:contains:value> | <field3:contains:value>...) and ' ', or '|'
                    "", // set filter operation (<fieldname:operator:value>)
                    "", // set groupby field(field, count)
                    "", // set sort field group(field/order,field/order,...)
                    "", // set categoryBoost(fieldname,matchType,boostID,boostKeyword)
                    "categoryId:1/SC|categoryName:1/SC", // set categoryGroupBy (fieldname:value)
                    "", // set categoryQuery (fieldname:value)
                    "", // set property group (fieldname,min,max, groupcount)
                    "serviceCode,tag,discountTag,serviceTag,wine25ItemKindName,wine25RegionSpName,itemCode,cardDiscountName,cardDiscountYn,adultYn,soldOutSp,deliverySp,stockCheckYn,recommendItemYn,storeCode,prmByQty,prmGtQty", // use check prefix query filed
                    "itemCode,sellPrice,discountPrice,discountRate,pickupDate,stockCount,starPoint,exposureSeq,pickupStartDate,pickupEndDate,supermarketItemCode", // set use check fast access field
                    "", // set multigroupby field
                    "", // set auth query (Auth Target Field, Auth Collection, Auth Reference Field, Authority Query)
                    "", // set Duplicate Detection Criterion Field, RANK/DESC,DATE/DESC
                    "giftcard" // collection display name
            }
            ,
            {
                    "exhibition", // set index name
                    "exhibition", // set collection name
                    "0,3",  // set pageinfo (start,count)
                    "1,0,0,0,0", // set query analyzer (useKMA,isCase,useOriginal,useSynonym, duplcated detection)
                    "RANK/DESC,DATE/DESC",  // set sort field (field,order) multi sort '/'
                    "basic,rpfmo,100",  // set sort field (field,order) multi sort '/'
                    "ssevntNm",// set search field
                    "DOCID,itemImageUrl,ssevntSeqno,ssevntNm,ssevntBeginDt,ssevntEndDt,pickupStartDate,pickupEndDate,remainTime,svcKindCd,ALIAS",// set document field
                    "", // set date range
                    "", // set rank range
                    "", // set prefix query, example: <fieldname:contains:value1>|<fieldname:contains:value2>/1,  (fieldname:contains:value) and ' ', or '|', not '!' / operator (AND:1, OR:0)
                    "", // set collection query (<fieldname:contains:value^weight | value^weight>/option...) and ' ', or '|'
                    "", // set boost query (<fieldname:contains:value> | <field3:contains:value>...) and ' ', or '|'
                    "", // set filter operation (<fieldname:operator:value>)
                    "", // set groupby field(field, count)
                    "", // set sort field group(field/order,field/order,...)
                    "", // set categoryBoost(fieldname,matchType,boostID,boostKeyword)
                    "", // set categoryGroupBy (fieldname:value)
                    "", // set categoryQuery (fieldname:value)
                    "", // set property group (fieldname,min,max, groupcount)
                    "svcKindCd", // use check prefix query filed
                    "pickupStartDate,pickupEndDate,svcKindCd", // set use check fast access field
                    "", // set multigroupby field
                    "", // set auth query (Auth Target Field, Auth Collection, Auth Reference Field, Authority Query)
                    "", // set Duplicate Detection Criterion Field, RANK/DESC,DATE/DESC
                    "exhibition" // collection display name
            }
            ,
            {
                    "store", // set index name
                    "store", // set collection name
                    "0,3",  // set pageinfo (start,count)
                    "1,0,0,0,0", // set query analyzer (useKMA,isCase,useOriginal,useSynonym, duplcated detection)
                    "RANK/DESC,DATE/DESC",  // set sort field (field,order) multi sort '/'
                    "basic,rpfmo,100",  // set sort field (field,order) multi sort '/'
                    "storeName,storePropertyBane",// set search field
                    "DOCID,storeCode,storeName,storeAddress,storeTelno,saleBeginTime,saleEndTime,xCoordinates,yCoordinates,townCode,storePropertyCode,storePropertyBane,ALIAS",// set document field
                    "", // set date range
                    "", // set rank range
                    "", // set prefix query, example: <fieldname:contains:value1>|<fieldname:contains:value2>/1,  (fieldname:contains:value) and ' ', or '|', not '!' / operator (AND:1, OR:0)
                    "", // set collection query (<fieldname:contains:value^weight | value^weight>/option...) and ' ', or '|'
                    "", // set boost query (<fieldname:contains:value> | <field3:contains:value>...) and ' ', or '|'
                    "", // set filter operation (<fieldname:operator:value>)
                    "", // set groupby field(field, count)
                    "", // set sort field group(field/order,field/order,...)
                    "", // set categoryBoost(fieldname,matchType,boostID,boostKeyword)
                    "", // set categoryGroupBy (fieldname:value)
                    "", // set categoryQuery (fieldname:value)
                    "", // set property group (fieldname,min,max, groupcount)
                    "storeCode,townCode,storePropertyCode", // use check prefix query filed
                    "", // set use check fast access field
                    "", // set multigroupby field
                    "", // set auth query (Auth Target Field, Auth Collection, Auth Reference Field, Authority Query)
                    "", // set Duplicate Detection Criterion Field, RANK/DESC,DATE/DESC
                    "store" // collection display name
            }
    };
}
