package com.gsretail.gst.b2e.search.search.service;

import com.gsretail.gst.b2e.search.search.model.Search;
import org.json.simple.JSONObject;

/**
 * com.gsretail.gst.b2e.search.search.service
 * ChatBotService
 *
 * @author : USER
 * @date : 2022-06-22
 * @tags :
 */
public interface ChatBotService {
    JSONObject getChatBotSearch(Search search);
}
