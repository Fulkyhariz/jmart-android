package com.fulkyJmartRK.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.fulkyJmartRK.jmart_android.model.Product;
import com.fulkyJmartRK.jmart_android.model.ProductCategory;

import java.util.Locale;

public class ItemDetailsActivity extends AppCompatActivity {

    TextView nama;
    TextView harga;
    TextView kategori;
    TextView status;
    TextView berat;
    TextView diskon;
    TextView shipment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        Product item= (Product) bundle.getSerializable("item");

        nama = findViewById(R.id.name_item_detail);
        harga = findViewById(R.id.price_item_detail);
        kategori = findViewById(R.id.category_item_detail);
        status = findViewById(R.id.status_item_detail);
        berat = findViewById(R.id.weight_item_detail);
        diskon = findViewById(R.id.discount_item_detail);
        kategori = findViewById(R.id.shipment_item_detail);

        nama.setText(item.name);
        harga.setText("Rp " + String.format("%.2f",item.price));
        kategori.setText(String.valueOf(item.category).toLowerCase(Locale.ROOT));
        status.setText(getStatus(item.conditionUsed));
        berat.setText(item.weight + " Kg");
        diskon.setText(String.format("%.2f",item.discount) + "%");
        kategori.setText(String.valueOf(item.category));
    }

    private String getStatus(boolean isUsed){
        if (isUsed) return "Bekas";
        else return "Baru";
    }
    private String getShipment(ProductCategory kategori){
        return "";
    }
}