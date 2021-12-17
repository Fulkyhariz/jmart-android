package com.fulkyJmartRK.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.fulkyJmartRK.jmart_android.adapter.PaymentAdapter;
import com.fulkyJmartRK.jmart_android.adapter.ProductsAdapter;
import com.fulkyJmartRK.jmart_android.model.Filter;
import com.fulkyJmartRK.jmart_android.model.Payment;
import com.fulkyJmartRK.jmart_android.model.Product;
import com.fulkyJmartRK.jmart_android.request.RequestFactory;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class InvoiceHistoryActivity extends AppCompatActivity implements Response.Listener<String>, Response.ErrorListener{

    ArrayList<Payment> payments;
    androidx.appcompat.widget.AppCompatButton storeInvoiceBtn;
    ListView invoiceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_history);

        storeInvoiceBtn = findViewById(R.id.switch_history_store);
        invoiceList = findViewById(R.id.list_history_user);

        requestList();
    }

    private void requestList(){
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(RequestFactory.getPage("payment", 0, 10, this, this));
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(String response) {
        payments = new ArrayList<>();
        try{
            JSONArray arr = new JSONArray(response);
            if(arr != null){
                for (int i = 0; i < arr.length(); i++){
                    Payment payment = new Payment(arr.getJSONObject(i));
                    payments.add(payment);
                }
                PaymentAdapter adapter = new PaymentAdapter(this, payments);
                invoiceList.setAdapter(adapter);
            }
        }
        catch(JSONException e){
            e.printStackTrace();
        }
    }
}