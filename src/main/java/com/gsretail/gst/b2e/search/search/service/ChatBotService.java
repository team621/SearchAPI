package com.gsretail.gst.b2e.search.search.service;

import org.json.simple.JSONObject;
import com.gsretail.gst.b2e.search.search.model.Search;

/**
 * com.gsretail.gst.b2e.search.search.service
 * ChatBotService
 *
 * @author : WISENUT
 * @date : 2022-06-22
 * @tags : ChatBot Search Service
 */
public interface ChatBotService {
    /**
     * 챗봇 검색 결과 호출.
     *
     * @param search     검색 객체
     * @return 검색 결과 (JSON)
     */
    JSONObject getChatBotSearch(Search search);
}
