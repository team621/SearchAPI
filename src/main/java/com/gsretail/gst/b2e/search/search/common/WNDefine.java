package com.gsretail.gst.b2e.search.search.common;

/**
 * packageName    : com.gsretail.gst.b2e.search.search.common
 * fileName       : WNDefine
 * author         : USER
 * date           : 2022-06-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-07        USER       최초 생성
 */
public class WNDefine {

    public final static int CONNECTION_TIMEOUT = 20000;
    public final static String CHARSET = "UTF-8";
    public final static int REALTIME_COUNT=100;
    public final static int PAGE_SCALE = 10; //view page list count

    public final static int CONNECTION_KEEP = 0; //recevive mode
    public final static int CONNECTION_REUSE = 2;
    public final static int CONNECTION_CLOSE = 3;

    public final static int ASC = 0; //order
    public final static int DESC = 1; //order

    public final static int USE_KMA_OFFOFF = 0; //synonym, morpheme
    public final static int USE_KMA_ONON = 1;
    public final static int USE_KMA_ONOFF = 2;
    public final static int USE_RESULT_STRING = 0; //result data type
    public final static int USE_RESULT_XML = 1;
    public final static int USE_RESULT_JSON = 2;
    public final static int USE_RESULT_DUPLICATE_STRING = 3; //uid result data type
    public final static int USE_RESULT_DUPLICATE_XML = 4;
    public final static int USE_RESULT_DUPLICATE_JSON = 5;

    public final static int IS_CASE_ON = 1; //case on, off
    public final static int IS_CASE_OFF = 0;

    public final static int HI_SUM_OFFOFF = 0; //summarizing, highlighting
    public final static int HI_SUM_OFFON = 1;
    public final static int HI_SUM_ONOFF = 2;
    public final static int HI_SUM_ONON = 3;

    public final static int COMMON_OR_WHEN_NORESULT_OFF = 0;
    public final static int COMMON_OR_WHEN_NORESULT_ON = 1;

    public final static int INDEX_NAME = 0;
    public final static int COLLECTION_NAME = 1;
    public final static int PAGE_INFO = 2;
    public final static int ANALYZER = 3;
    public final static int SORT_FIELD = 4;
    public final static int RANKING_OPTION = 5;
    public final static int SEARCH_FIELD = 6;
    public final static int RESULT_FIELD = 7;
    public final static int DATE_RANGE = 8;
    public final static int RANK_RANGE = 9;
    public final static int EXQUERY_FIELD = 10;
    public final static int COLLECTION_QUERY =11;
    public final static int BOOST_QUERY =12;
    public final static int FILTER_OPERATION = 13;
    public final static int GROUP_BY = 14;
    public final static int GROUP_SORT_FIELD = 15;
    public final static int CATEGORY_BOOST = 16;
    public final static int CATEGORY_GROUPBY = 17;
    public final static int CATEGORY_QUERY = 18;
    public final static int PROPERTY_GROUP = 19;
    public final static int PREFIX_FIELD = 20;
    public final static int FAST_ACCESS = 21;
    public final static int MULTI_GROUP_BY = 22;
    public final static int AUTH_QUERY = 23;
    public final static int DEDUP_SORT_FIELD = 24;
    public final static int COLLECTION_KOR = 25;

    public final static int MERGE_COLLECTION_NAME = 0;
    public final static int MERGE_MAPPING_COLLECTION_NAME = 1;
    public final static int MERGE_PAGE_INFO = 2;
    public final static int MERGE_RESULT_FIELD = 3;
    public final static int MERGE_MAPPING_RESULT_FIELD = 4;
    public final static int MERGE_MULTI_GROUP_BY_FIELD = 5;
    public final static int MERGE_MAPPING_MULTI_GROUP_BY_FIELD = 6;
    public final static int MERGE_CATEGORY_GROUPBY_FIELD = 7;
    public final static int MERGE_MAPPING_CATEGORY_GROUPBY_FIELD = 8;
}
