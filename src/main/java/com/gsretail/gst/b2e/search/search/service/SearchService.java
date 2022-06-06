/*
 * Copyright (c) GS Retail.
 * All rights reserved.
 * 모든 권한은 GS리테일에 있으며,
 * GS리테일의 허락없이 소스 및 이진형식을 재배포, 사용하는 행위를 금지합니다.
 */

package com.gsretail.gst.b2e.search.search.service;

import com.gsretail.gst.b2e.search.search.model.Search;
import org.json.simple.JSONObject;

/**
 * com.gsretail.gst.b2e.search.search.service
 * SearchService
 *
 * @author : USER
 * @date : 2022-05-17
 * @tags :
 */
public interface SearchService {
    /**
     * 검색 결과 호출.
     *
     * @param search     검색 객체
     * @return 검색 결과 (JSON)
     */
    public JSONObject getSearchResult(Search search);
    public JSONObject getStoreSearchResult(Search search);
    public JSONObject getStoreStockSearchResult(Search search);
}
