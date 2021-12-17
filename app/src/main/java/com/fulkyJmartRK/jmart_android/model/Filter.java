package com.fulkyJmartRK.jmart_android.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Filter implements java.io.Serializable {
    public static int accountId;
    public static ProductCategory category;
    public static boolean isUsed;
    public static boolean isNew;
    public static String name = "belum diganti";
    public static double maxPrice;
    public static double minPrice;
    public static boolean isFiltered = false;
    public static int categoryPos = 0;
/*

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
    }*/
}
