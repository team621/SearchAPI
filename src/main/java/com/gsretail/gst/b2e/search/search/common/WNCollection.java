package com.gsretail.gst.b2e.search.search.common;

import java.util.Properties;

import static java.lang.System.getProperties;

/**
 * packageName    : com.gsretail.gst.b2e.search.search.common
 * fileName       : WNCollection
 * author         : USER
 * date           : 2022-06-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-07        USER       최초 생성
 */
public class WNCollection {
    static String SEARCH_IP = "27.122.138.23";
    static int SEARCH_PORT = 5052;
    //운영
    public String[] COLLECTIONS = new String[]{"oneplus", "thefresh", "woodel_gs", "woodel_mart", "gs25_reservation", "wine25_gs"};
    public String[] COLLECTIONS_NAME = new String[]{"oneplus", "thefresh", "woodel_gs", "woodel_mart", "gs25_reservation", "wine25_gs"};

    public String[] MERGE_COLLECTIONS = new String[]{"mergeCollection"};

    public String[][] MERGE_COLLECTION_INFO = new String[][]
            {
                    {
                            "mergeCollection", // set merge collection name
                            "woodel_gs/woodel_mart", // set collection name, delimiter: /
                            "0,3",  // set merge collection pageinfo (start,count)
                            "itemImageUrl,itemName,sellPrice,discountPrice,itemCode,supermarketItemCode,ALIAS",// set merge document field
                            "itemImageUrl,itemName,sellPrice,discountPrice,itemCode,supermarketItemCode,ALIAS/itemImageUrl,itemName,sellPrice,discountPrice,itemCode,supermarketItemCode,ALIAS", // set document field, delimiter: /
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
                    "DOCID,itemImageUrl,serviceCode,tag,discountTag,serviceTag,wine25ItemKindName,wine25RegionSpName,itemCode,itemName,shortItemName,sellPrice,discountPrice,discountRate,discountAmount,cardDiscountName,cardDiscountYn,extraPresentItemName,extraPresentItemShortName,extraPresentImageUrl,adultYn,pickupDate,stockCount,buyQuantity,soldOutSp,deliverySp,starPoint,prmByQty,prmGtQty,stockCheckYn,exposureSeq,categoryId,categoryName,recommendItemYn,storeName,storeCode,pickupStartDate,pickupEndDate,prtnitemcd,ALIAS",// set document field
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
                    "", // set groupby field(field, count)
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
                    "", // set groupby field(field, count)
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
                    "woodel_mart", // set index name
                    "woodel_mart", // set collection name
                    "0,3", // set pageinfo (start,count)
                    "1,1,1,1,0", // set query analyzer (useKMA,isCase,useOriginal,useSynonym, duplcated detection)
                    "RANK/DESC,DATE/DESC", // set sort field (field,order) multi sort '/'
                    "basic,rpfmo,100", // set sort field (field,order) multi sort '/'
                    "itemName,shortItemName,storeName",// set search field
                    "DOCID,itemImageUrl,serviceCode,tag,discountTag,serviceTag,wine25ItemKindName,wine25RegionSpName,itemCode,itemName,shortItemName,sellPrice,discountPrice,discountRate,discountAmount,cardDiscountName,cardDiscountYn,extraPresentItemName,extraPresentItemShortName,extraPresentImageUrl,adultYn,pickupDate,stockCount,buyQuantity,soldOutSp,deliverySp,starPoint,prmByQty,prmGtQty,stockCheckYn,exposureSeq,categoryId,categoryName,recommendItemYn,storeName,storeCode,pickupStartDate,pickupEndDate,wdlvyItemSpCd,optItemIncldYn,supermarketItemCode,evntKindCd,ALIAS",// set document field
                    "", // set date range
                    "", // set rank range
                    "", // set prefix query, example: <fieldname:contains:value1>|<fieldname:contains:value2>/1, (fieldname:contains:value) and ' ', or '|', not '!' / operator (AND:1, OR:0)
                    "", // set collection query (<fieldname:contains:value^weight | value^weight>/option...) and ' ', or '|'
                    "", // set boost query (<fieldname:contains:value> | <field3:contains:value>...) and ' ', or '|'
                    "", // set filter operation (<fieldname:operator:value>)
                    "", // set groupby field(field, count)
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
                    "gs25_reservation", // set index name
                    "gs25_reservation", // set collection name
                    "0,3", // set pageinfo (start,count)
                    "1,1,1,1,0", // set query analyzer (useKMA,isCase,useOriginal,useSynonym, duplcated detection)
                    "RANK/DESC,DATE/DESC", // set sort field (field,order) multi sort '/'
                    "basic,rpfmo,100", // set sort field (field,order) multi sort '/'
                    "itemName,shortItemName,storeName",// set search field
                    "DOCID,itemImageUrl,serviceCode,tag,discountTag,serviceTag,wine25ItemKindName,wine25RegionSpName,itemCode,itemName,shortItemName,sellPrice,discountPrice,discountRate,discountAmount,cardDiscountName,cardDiscountYn,extraPresentItemName,extraPresentItemShortName,extraPresentImageUrl,adultYn,pickupDate,stockCount,buyQuantity,soldOutSp,deliverySp,starPoint,prmByQty,prmGtQty,stockCheckYn,exposureSeq,categoryId,categoryName,recommendItemYn,storeName,storeCode,pickupStartDate,pickupEndDate,wdlvyItemSpCd,optItemIncldYn,supermarketItemCode,ALIAS",// set document field
                    "", // set date range
                    "", // set rank range
                    "", // set prefix query, example: <fieldname:contains:value1>|<fieldname:contains:value2>/1, (fieldname:contains:value) and ' ', or '|', not '!' / operator (AND:1, OR:0)
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
                    "0,3", // set pageinfo (start,count)
                    "1,1,1,1,0", // set query analyzer (useKMA,isCase,useOriginal,useSynonym, duplcated detection)
                    "RANK/DESC,DATE/DESC", // set sort field (field,order) multi sort '/'
                    "basic,rpfmo,100", // set sort field (field,order) multi sort '/'
                    "itemName,shortItemName,storeName",// set search field
                    "DOCID,itemImageUrl,serviceCode,tag,discountTag,serviceTag,wine25ItemKindName,wine25RegionSpName,itemCode,itemName,shortItemName,sellPrice,discountPrice,discountRate,discountAmount,cardDiscountName,cardDiscountYn,extraPresentItemName,extraPresentItemShortName,extraPresentImageUrl,adultYn,pickupDate,stockCount,buyQuantity,soldOutSp,deliverySp,starPoint,prmByQty,prmGtQty,stockCheckYn,exposureSeq,categoryId,categoryName,recommendItemYn,storeName,storeCode,pickupStartDate,pickupEndDate,wdlvyItemSpCd,optItemIncldYn,supermarketItemCode,ALIAS",// set document field
                    "", // set date range
                    "", // set rank range
                    "", // set prefix query, example: <fieldname:contains:value1>|<fieldname:contains:value2>/1, (fieldname:contains:value) and ' ', or '|', not '!' / operator (AND:1, OR:0)
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
                    "wine25_gs" // collection display name
            }
    };
}
