/*
 * Copyright (c) GS Retail.
 * All rights reserved.
 * 모든 권한은 GS리테일에 있으며,
 * GS리테일의 허락없이 소스 및 이진형식을 재배포, 사용하는 행위를 금지합니다.
 */

package com.gsretail.gst.b2e.search.search.controller;

import com.gsretail.gst.b2e.search.search.model.Search;
import com.gsretail.gst.b2e.search.search.service.SearchService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

/**
 * com.gsretail.gst.b2e.search.search.controller
 * SearchController
 *
 * @author : WISENUT
 * @date : 2022-05-16
 * @tags :
 */
@RestController
public class SearchController {
    @Autowired
    private SearchService searchService;

    /**
     * 검색 결과 호출.
     *
     * @param request the request
     */
    @RequestMapping(value = "/search/v1/totalSearch", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject getSearchResult(HttpServletRequest request){
        Search search = setSearchParameter(request);

        JSONObject searchResultJson = searchService.getSearchResult(search);

        return searchResultJson;
    }

    @RequestMapping(value = "/search/v1/storeCodeSearch", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject getStoreCodeSearch(HttpServletRequest request){
        Search search = setSearchParameter(request);

        JSONObject searchResultJson = searchService.getSearchResult(search);

        return searchResultJson;
    }

    @RequestMapping(value = "/search/v1/storeStockSearch", method = {RequestMethod.POST, RequestMethod.GET})
    public void getStoreStock(){}

    private Search setSearchParameter(HttpServletRequest request) {
        Search search = null;

        StringBuffer requestBuffer = new StringBuffer();
        String line = null;

        try{
            BufferedReader reader = request.getReader();
            while((line = reader.readLine()) != null) requestBuffer.append(line);

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(requestBuffer.toString());

            search = new Search(jsonObj);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return search;
    }
}
