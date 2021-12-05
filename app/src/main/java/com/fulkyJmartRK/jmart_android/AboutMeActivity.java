package com.fulkyJmartRK.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.fulkyJmartRK.jmart_android.model.Account;

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

        loggedAccount = LoginActivity.getLoggedAccount();
        nameAccount.setText(loggedAccount.name);
        emailAccount.setText(loggedAccount.email);
        balanceAccount.setText(String.valueOf(loggedAccount.balance));
    }
}