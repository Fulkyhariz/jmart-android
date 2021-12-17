package com.fulkyJmartRK.jmart_android.request;

import java.util.*;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.fulkyJmartRK.jmart_android.model.ProductCategory;

public class RequestFactory {
    private static final String URL_FORMAT_ID = "http://10.0.2.2:8080/%s/{id}?id=%d";
    private static final String URL_FORMAT_ID_STORE = "http://10.0.2.2:8080/%s/{id}/getStore?id=%d&accountId=%d";
    private static final String URL_FORMAT_PAGE = "http://10.0.2.2:8080/%s/page?page=%s&pageSize=%s";
    private static final String URL_FORMAT_PAGE_FILTER = "http://10.0.2.2:8080/product/getFiltered?page=%s&pageSize=%s&search=%s" +
            "&maxPrice=%f&minPrice=%f&isUsed=%s&isNew=%s&category=%s";

    public static StringRequest getById
            (String parentURL,
             int id,
             Response.Listener<String> listener,
             Response.ErrorListener errorListener){
        String url = String.format(URL_FORMAT_ID, parentURL, id);
        return new StringRequest(Request.Method.GET, url, listener, errorListener);
    }

    public static StringRequest getByIdStore
            (String parentURL,
             int id,
             int accountId,
             Response.Listener<String> listener,
             Response.ErrorListener errorListener){
        String url = String.format(URL_FORMAT_ID_STORE, parentURL, id, accountId);
        return new StringRequest(Request.Method.GET, url, listener, errorListener);
    }

    public static StringRequest getPage
            (
                    String parentURI,
                    int page,
                    int pageSize,
                    Response.Listener<String> listener,
                    Response.ErrorListener errorListener
            )
    {
        String url = String.format(URL_FORMAT_PAGE, parentURI, page, pageSize);
        return new StringRequest(Request.Method.GET, url, listener, errorListener);
    }

    public static StringRequest getPageFiltered
            (
                    int page,
                    int pageSize,
                    String search,
                    double minPrice,
                    double maxPrice,
                    Boolean isUsed,
                    Boolean isNew,
                    ProductCategory category,
                    Response.Listener<String> listener,
                    Response.ErrorListener errorListener
            )
    {
        String url = String.format(URL_FORMAT_PAGE_FILTER, page, pageSize, search, maxPrice, minPrice, isUsed, isNew, category);
        System.out.println(url);
        return new StringRequest(Request.Method.GET, url, listener, errorListener);
    }
}
