package com.fulkyJmartRK.jmart_android.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Product extends Serializable {
    public int accountId;
    public ProductCategory category;
    public boolean conditionUsed;
    public double discount;
    public String name;
    public double price;
    public byte shipmentPlans;
    public int weight;

    public Product(JSONObject object){
        try {
            this.accountId = object.getInt("accountId");
            this.name = object.getString("name");
            this.weight = object.getInt("weight");
            this.conditionUsed = object.getBoolean("conditionUsed");
            this.price = object.getDouble("price");
            this.category = ProductCategory.valueOf(object.getString("category"));
            this.shipmentPlans = Byte.valueOf(object.getString("shipmentPlans"));
            this.discount = object.getDouble("discount");
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

    }

/*    public String toString(){
        return this.name;
    }*/
}
