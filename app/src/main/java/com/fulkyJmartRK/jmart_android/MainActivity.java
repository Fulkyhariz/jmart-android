package com.fulkyJmartRK.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.fulkyJmartRK.jmart_android.model.Account;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView user = findViewById(R.id.helloUser);
        Account account = LoginActivity.getLoggedAccount();
        user.setText(account.name);
    }
}