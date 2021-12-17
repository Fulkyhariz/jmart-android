package com.fulkyJmartRK.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.fulkyJmartRK.jmart_android.model.Product;
import com.fulkyJmartRK.jmart_android.model.ProductCategory;
import com.fulkyJmartRK.jmart_android.request.CreateCustomerInvoiceRequest;
import com.fulkyJmartRK.jmart_android.request.CreateProductRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

public class ItemDetailsActivity extends AppCompatActivity {

    Gson gson = new Gson();

    TextView nama;
    TextView harga;
    TextView kategori;
    TextView status;
    TextView berat;
    TextView diskon;
    TextView shipment;
    TextView totalAmount;
    EditText itemAmount;
    EditText address;
    androidx.appcompat.widget.AppCompatButton calculateTotalBtn;
    androidx.appcompat.widget.AppCompatButton buyItemBtn;

    Product item;

    int amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        item= (Product) bundle.getSerializable("item");

        nama = findViewById(R.id.name_item_detail);
        harga = findViewById(R.id.price_item_detail);
        kategori = findViewById(R.id.category_item_detail);
        status = findViewById(R.id.status_item_detail);
        berat = findViewById(R.id.weight_item_detail);
        diskon = findViewById(R.id.discount_item_detail);
        shipment = findViewById(R.id.shipment_item_detail);
        totalAmount = findViewById(R.id.total_payment);
        itemAmount = findViewById(R.id.item_amount);
        address = findViewById(R.id.item_address);
        calculateTotalBtn = findViewById(R.id.calculate_total_button);
        buyItemBtn = findViewById(R.id.buy_item_button);

        nama.setText(item.name);
        harga.setText("Rp " + String.format("%.2f",item.price));
        kategori.setText(String.valueOf(item.category));
        status.setText(getStatus(item.conditionUsed));
        berat.setText(item.weight + " Kg");
        diskon.setText(String.format("%.2f",item.discount) + "%");
        shipment.setText(getShipment(item.shipmentPlans));

        calculateTotalBtn.setOnClickListener(this::getTotalPayment);
        buyItemBtn.setOnClickListener(this::buyItem);
    }

    private String getStatus(boolean isUsed){
        if (isUsed) return "Bekas";
        else return "Baru";
    }

    private String getShipment(byte shipment){
        String value;
        if (shipment == (1<<0)) value = "INSTANT";
        else if (shipment == (1<<1)) value = "KARGO";
        else if (shipment == (1<<2)) value ="NEXT DAY";
        else if (shipment == (1<<3)) value = "REGULER";
        else if (shipment == (1<<4)) value ="SAME DAY";
        else value = "";
        return value;
    }

    private void getTotalPayment(View view){
        amount = Integer.parseInt(itemAmount.getText().toString());
        double total = amount * item.price;
        totalAmount.setText(String.valueOf(total));
    }

    private void buyItem(View view){
        CreateCustomerInvoiceRequest req = new CreateCustomerInvoiceRequest(MainActivity.id, item.id, amount, address.getText().toString(), item.shipmentPlans,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject obj = new JSONObject(response);
                            if(obj != null){
                                Toast.makeText(ItemDetailsActivity.this, "Order Successful", Toast.LENGTH_LONG).show();
                                finish();
                                startActivity(getIntent());
                            }
                        }
                        catch(JSONException e){
                            Toast.makeText(ItemDetailsActivity.this, "Order Failed", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ItemDetailsActivity.this, "System Failure", Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(req);
    }
}