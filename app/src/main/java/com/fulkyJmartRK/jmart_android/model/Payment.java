package com.fulkyJmartRK.jmart_android.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Payment implements java.io.Serializable{
    public int buyerId;
    public int productId;
    public int productCount;
    public byte shipment;
    public String date;
    public String status;

    public Payment(JSONObject object){
        try {
            this.buyerId = object.getInt("buyerId");
            this.productId = object.getInt("productId");
            this.productCount = object.getInt("productCount");
            this.shipment = (byte) object.getJSONObject("shipment").getInt("plan");
            this.date = object.getString("date");
            this.status = object.getJSONObject("history").getString("status");
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public Payment(int buyerId,
                       int productId,
                       int productCount,
                       byte shipment){
        this.buyerId = buyerId;
        this.productId = productId;
        this.productCount = productCount;
        this.shipment = shipment;
    }
}
