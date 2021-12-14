package com.fulkyJmartRK.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.*;
import com.android.volley.toolbox.Volley;
import com.fulkyJmartRK.jmart_android.model.*;
import com.fulkyJmartRK.jmart_android.request.RegisterStoreRequest;
import com.fulkyJmartRK.jmart_android.request.TopUpRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class AboutMeActivity extends AppCompatActivity {
    Account loggedAccount;
    TextView nameAccount;
    TextView emailAccount;
    TextView balanceAccount;
    androidx.appcompat.widget.AppCompatButton topUpBtn;
    EditText topUpAmount;
    androidx.appcompat.widget.AppCompatButton registerStoreBtn;
    EditText nameStoreReg;
    EditText addressStoreReg;
    EditText phoneStoreReg;
    androidx.appcompat.widget.AppCompatButton confirmButton;
    androidx.appcompat.widget.AppCompatButton cancelButton;
    TextView nameStore;
    TextView addressStore;
    TextView phoneStore;
    androidx.cardview.widget.CardView registerCardView;
    androidx.cardview.widget.CardView showStoreCardView;

    public static final Gson gson = new Gson();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        nameAccount = findViewById(R.id.name_detail);
        emailAccount = findViewById(R.id.email_detail);
        balanceAccount = findViewById(R.id.balance_detail);
        topUpBtn = findViewById(R.id.topup_button);
        topUpAmount = findViewById(R.id.topup_amount);
        registerStoreBtn = findViewById(R.id.register_store_button);
        nameStoreReg = findViewById(R.id.register_store_name);
        addressStoreReg = findViewById(R.id.register_store_address);
        phoneStoreReg = findViewById(R.id.register_store_phone);
        confirmButton = findViewById(R.id.register_store_button_confirm);
        cancelButton = findViewById(R.id.register_store_button_cancel);
        nameStore = findViewById(R.id.name_store);
        addressStore = findViewById(R.id.address_store);
        phoneStore = findViewById(R.id.phone_number_store);
        registerCardView = findViewById(R.id.register_store_card_view);
        showStoreCardView = findViewById(R.id.store_card_view);

        loggedAccount = LoginActivity.getLoggedAccount();
        nameAccount.setText(loggedAccount.name);
        emailAccount.setText(loggedAccount.email);
        balanceAccount.setText(String.valueOf(loggedAccount.balance));

        topUp();

        if (loggedAccount.store == null){
            registerCardView.setVisibility(View.GONE);
            showStoreCardView.setVisibility(View.INVISIBLE);
            registerStore();
        }else{
            displayStore();
        }
    }

    private void displayStore() {
        registerStoreBtn.setVisibility(View.GONE);
        showStoreCardView.setVisibility(View.VISIBLE);
        nameStore.setText(loggedAccount.store.name);
        addressStore.setText(loggedAccount.store.address);
        phoneStore.setText(loggedAccount.store.phoneNumber);
    }

    private void registerStore(){
        registerStoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerCardView.setVisibility(View.VISIBLE);
                registerStoreBtn.setVisibility(View.GONE);
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerCardView.setVisibility(View.GONE);
                registerStoreBtn.setVisibility(View.VISIBLE);
            }
        });
        confirmButton.setOnClickListener(this::onConfirmClick);
    }

    private void onConfirmClick(View view) {
        String nameStore = nameStoreReg.getText().toString();
        String addressStore = addressStoreReg.getText().toString();
        String phoneStore = phoneStoreReg.getText().toString();
        int id = 1;
        System.out.println(id);

        RegisterStoreRequest req = new RegisterStoreRequest(id, nameStore, addressStore, phoneStore,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject obj = new JSONObject(response);
                            if(obj != null){
                                Toast.makeText(AboutMeActivity.this, "Register Store Successful", Toast.LENGTH_LONG).show();
                                loggedAccount.store = gson.fromJson(obj.toString(), Store.class);
                                finish();
                                startActivity(getIntent());
                            }
                        }
                        catch(JSONException e){
                            Toast.makeText(AboutMeActivity.this, "Register Store Failed", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AboutMeActivity.this, "System Failure", Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(req);
    }

    private void topUp() {
        topUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topup = topUpAmount.getText().toString();
                System.out.println(topup);
                double balance = Double.parseDouble(topup);
                System.out.println(balance);
                int id = 1;
                TopUpRequest req = new TopUpRequest(id, balance,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.equals("true")){
                                    Toast.makeText(AboutMeActivity.this, "Top Up Successful", Toast.LENGTH_LONG).show();
                                    loggedAccount.balance += balance;
                                    finish();
                                    startActivity(getIntent());
                                }
                                else{
                                    Toast.makeText(AboutMeActivity.this, "Top Up Failed", Toast.LENGTH_LONG).show();
                                    return;
                                }

                            }
                        }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AboutMeActivity.this, "System Failure", Toast.LENGTH_LONG).show();
                    }
                });
                RequestQueue queue = Volley.newRequestQueue(AboutMeActivity.this);
                queue.add(req);
            }
        });
    }
}