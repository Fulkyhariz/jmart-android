package com.fulkyJmartRK.jmart_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

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
import com.fulkyJmartRK.jmart_android.model.Account;
import com.fulkyJmartRK.jmart_android.request.LoginRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity
implements Response.Listener<String>, Response.ErrorListener{
    private static final Gson gson = new Gson();
    private static Account loggedAccount;

    private EditText emailInput;
    private EditText passwordInput;
    private AppCompatButton loginBtn;
    private TextView registerBtn;

    public static Account getLoggedAccount(){
        return loggedAccount;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailInput = findViewById(R.id.email);
        passwordInput = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginButton);
        registerBtn = findViewById(R.id.registerBtn);

        //emailInput.getText().toString();

        registerBtn.setOnClickListener(this::onRegisterClick);
        loginBtn.setOnClickListener(this::onLoginClick);
    }

    private void onLoginClick(View view){
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

        LoginRequest req = new LoginRequest(email, password, this, this);
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(req);
    }

    private void onRegisterClick(View view){
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }

    @Override
    public void onResponse(String response){
        Intent i = new Intent(this, MainActivity.class);
        try{
            JSONObject obj = new JSONObject(response);
            i.putExtra("id", obj.getInt("id"));
            loggedAccount = gson.fromJson(obj.toString(), Account.class);
        } catch (Exception e) {
            Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show();
            return;
        }
        Toast.makeText(this, "Login Successful", Toast.LENGTH_LONG).show();
        startActivity(i);
    }

    @Override
    public void onErrorResponse(VolleyError error){
        Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show();
    }
}