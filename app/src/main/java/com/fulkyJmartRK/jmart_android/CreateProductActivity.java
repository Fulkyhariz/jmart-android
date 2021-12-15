package com.fulkyJmartRK.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.fulkyJmartRK.jmart_android.model.Account;
import com.fulkyJmartRK.jmart_android.model.Product;
import com.fulkyJmartRK.jmart_android.model.ProductCategory;
import com.fulkyJmartRK.jmart_android.model.Store;
import com.fulkyJmartRK.jmart_android.request.CreateProductRequest;
import com.fulkyJmartRK.jmart_android.request.RegisterStoreRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class CreateProductActivity extends AppCompatActivity {

    Gson gson = new Gson();

    Account loggedAccount = LoginActivity.getLoggedAccount();

    EditText nameInput;
    EditText weightInput;
    EditText priceInput;
    EditText discountInput;
    RadioButton newRadio;
    RadioButton usedRadio;
    RadioGroup conditionRadio;
    Spinner categorySpinner;
    Spinner shipmentPlanSpinner;
    androidx.appcompat.widget.AppCompatButton applyButton;
    androidx.appcompat.widget.AppCompatButton clearButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);

        nameInput = findViewById(R.id.create_product_name);
        weightInput = findViewById(R.id.create_weight);
        priceInput = findViewById(R.id.create_price);
        discountInput = findViewById(R.id.create_discount);
        newRadio = findViewById(R.id.radio_new);
        usedRadio = findViewById(R.id.radio_used);
        conditionRadio = findViewById(R.id.radio_condition);
        categorySpinner = findViewById(R.id.create_category_spinner);
        shipmentPlanSpinner = findViewById(R.id.create_shipment_spinner);
        applyButton = findViewById(R.id.apply_button);
        clearButton = findViewById(R.id.clear_button);

        clearButton.setOnClickListener(this::onClearClicked);
        applyButton.setOnClickListener(this::onApplyClicked);
    }

    private void onClearClicked(View view){
        nameInput.setText("");
        weightInput.setText("");
        priceInput.setText("");
        discountInput.setText("");
        conditionRadio.clearCheck();
        categorySpinner.setSelection(0);
        shipmentPlanSpinner.setSelection(0);
    }

    private byte getShipmentByte(String shipment){
        byte value;
        if (shipment.equals("INSTANT")) value = (byte) (1<<0);
        else if (shipment.equals("KARGO")) value = (1<<1);
        else if (shipment.equals("NEXT DAY")) value = (1<<2);
        else if (shipment.equals("REGULER")) value = (1<<3);
        else if (shipment.equals("SAME DAY")) value = (1<<4);
        else value = 0;
        System.out.println(value);
        return value;
    }

    private boolean getProductCondition(){
        if (usedRadio.isChecked()) return true;
        else return false;
    }

    private void onApplyClicked(View view){
        String productName = nameInput.getText().toString();
        int productWeight = Integer.parseInt(weightInput.getText().toString());
        double productPrice = Double.parseDouble(priceInput.getText().toString());
        double productDiscount = Double.parseDouble(discountInput.getText().toString());
        Boolean productCondition = getProductCondition();
        ProductCategory productCategory = ProductCategory.valueOf(categorySpinner.getSelectedItem().toString());
        byte productShipment = getShipmentByte(shipmentPlanSpinner.getSelectedItem().toString());
        System.out.println(shipmentPlanSpinner.getSelectedItem().toString());
        int id = 1;
        System.out.println(id);

        CreateProductRequest req = new CreateProductRequest(id,productName, productWeight, productCondition, productPrice, productDiscount, productCategory, productShipment,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject obj = new JSONObject(response);
                            if(obj != null){
                                Toast.makeText(CreateProductActivity.this, "Create Product Successful", Toast.LENGTH_LONG).show();
                                finish();
                                startActivity(getIntent());
                            }
                        }
                        catch(JSONException e){
                            Toast.makeText(CreateProductActivity.this, "Create Product Failed", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CreateProductActivity.this, "System Failure", Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(req);
    }
}