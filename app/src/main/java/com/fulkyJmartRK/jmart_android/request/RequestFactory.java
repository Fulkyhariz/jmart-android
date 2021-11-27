package com.fulkyJmartRK.jmart_android.request;

import java.util.*;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

public class RequestFactory {
    private static final String URL_FORMAT_ID = "http://127.0.0.1:8080/%s/%d";
    private static final String URL_FORMAT_PAGE = "http://127.0.0.1:8080/%s/page";

    public static StringRequest getById
            (String parentURL,
             int id,
             Response.Listener<String> listener,
             Response.ErrorListener errorListener){
        String url = String.format(URL_FORMAT_ID, parentURL, id);
        return new StringRequest(Request.Method.GET, url, listener, errorListener);
    }

    public static StringRequest getPage
            (String parentURL,
             int page,
             int pageSize,
             Response.Listener<String> listener,
             Response.ErrorListener errorListener){
        String url = String.format(URL_FORMAT_PAGE, parentURL);
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        params.put("pageSize", String.valueOf(pageSize));
        return new StringRequest(Request.Method.GET, url, listener, errorListener){
            public Map<String, String> getParams(){return params;}
        };
    }
}
