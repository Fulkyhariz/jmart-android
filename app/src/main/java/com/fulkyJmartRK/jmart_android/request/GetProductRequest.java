package com.fulkyJmartRK.jmart_android.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class GetProductRequest extends StringRequest {

    public static final String URL = "http://10.0.2.2:8080/product/get";
    //public static final String URL = "http://10.0.2.2:8080/product/{id}/store";
    //private final Map<String, String> params;

    public GetProductRequest(/*int accountId,
                             int page,*/
                             Response.Listener<String> listener,
                             Response.ErrorListener errorListener){
        super(Method.GET, URL, listener, errorListener);
        /*System.out.println(accountId);
        params = new HashMap<>();
        params.put("id", String.valueOf(accountId));
        params.put("page", String.valueOf(page));
        params.put("pageSize", String.valueOf(10));*/
    }
/*    public  Map<String ,String> getParams(){
        return params;
    }*/
}
