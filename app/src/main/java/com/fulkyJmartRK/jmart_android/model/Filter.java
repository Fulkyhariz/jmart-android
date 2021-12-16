package com.fulkyJmartRK.jmart_android.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Filter implements java.io.Serializable {
    public int accountId;
    public ProductCategory category;
    public boolean isUsed;
    public boolean isNew;
    public String name;
    public double maxPrice;
    public double minPrice;


    public Filter(int accountId,
                  ProductCategory category,
                  boolean isUsed,
                  boolean isNew,
                  String name,
                  double maxPrice,
                  double minPrice){
        this.accountId = accountId;
        this.category = category;
        this.isUsed = isUsed;
        this.isNew = isNew;
        this.name = name;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
    }
}
