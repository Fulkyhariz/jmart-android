package com.fulkyJmartRK.jmart_android.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.fulkyJmartRK.jmart_android.model.ProductCategory;

import java.util.HashMap;
import java.util.Map;

public class CreateCustomerInvoiceRequest extends StringRequest {

    private static final String URL = "http://10.0.2.2:8080/payment/create";
    private final Map<String, String> params;

    public CreateCustomerInvoiceRequest(
            int buyerId,
            int productId,
            int productCount,
            String shipmentAddress,
            byte shipmentPlan,
            Response.Listener<String> listener, Response.ErrorListener errorListener
    ){
        super(Request.Method.POST, URL, listener, errorListener);
        params = new HashMap<>();
        params.put("buyerId", String.valueOf(buyerId));
        params.put("productId", String.valueOf(productId));
        params.put("productCount", String.valueOf(productCount));
        params.put("shipmentAddress", String.valueOf(shipmentAddress));
        params.put("shipmentPlan", String.valueOf(shipmentPlan));
    }
    public  Map<String ,String> getParams(){ return params; }
}
