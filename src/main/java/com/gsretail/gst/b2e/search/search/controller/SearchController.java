/*
 * Copyright (c) GS Retail.
 * All rights reserved.
 * 모든 권한은 GS리테일에 있으며,
 * GS리테일의 허락없이 소스 및 이진형식을 재배포, 사용하는 행위를 금지합니다.
 */

package com.gsretail.gst.b2e.search.search.controller;

import java.io.BufferedReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import javax.servlet.http.HttpServletRequest;
import com.gsretail.gst.b2e.search.search.model.Search;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gsretail.gst.b2e.search.search.service.SearchService;
import com.gsretail.gst.b2e.search.search.service.ChatBotService;

/**
 * com.gsretail.gst.b2e.search.search.controller
 * SearchController
 *
 * @author : WISENUT
 * @date : 2022-06-22
 * @tags : Search API Controller
 */
@RestController
public class SearchController {
    @Autowired private SearchService searchService;
    @Autowired private ChatBotService chatBotService;

    /**
     * 통합 검색 결과 호출.
     *
     * @param request the request
     */
    @RequestMapping(value = "/search/v1/totalSearch", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject getSearchResult(HttpServletRequest request){
        Search search = setSearchParameter(request);

        JSONObject searchResultJson = searchService.getTotalSearch(search);

        return searchResultJson;
    }

    /**
     * 챗봇 검색 결과 호출.
     *
     * @param request the request
     */
    @RequestMapping(value = "/search/v1/chatBotSearch", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject getChatBotSearchResult(HttpServletRequest request){
        Search search = setSearchParameter(request);

        JSONObject searchResultJson = chatBotService.getChatBotSearch(search);

        return searchResultJson;
    }

    /**
     * 검색 객체 설정 (search 객체)
     *
     * @param request the request
     */
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
