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
import com.fulkyJmartRK.jmart_android.request.LoginRequest;
import com.fulkyJmartRK.jmart_android.request.RegisterRequest;

import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity
implements Response.Listener<String>, Response.ErrorListener{

    private EditText emailInput;
    private EditText nameInput;
    private EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailInput = findViewById(R.id.email);
        passwordInput = findViewById(R.id.password);
        nameInput = findViewById(R.id.name);
        AppCompatButton registerbtn = findViewById(R.id.registerBtn);

        registerbtn.setOnClickListener(this::onRegisterClick);

    }

    private void onRegisterClick(View view){
        String name = nameInput.getText().toString();
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

        RegisterRequest req = new RegisterRequest(name ,email, password, this, this);
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(req);
    }

    @Override
    public void onResponse(String response){
        Intent i = new Intent(this, LoginActivity.class);
        try{
            JSONObject obj = new JSONObject(response);
            i.putExtra("id", obj.getInt("id"));
        } catch (Exception e) {
            Toast.makeText(this, "Register Failed", Toast.LENGTH_LONG).show();
            return;
        }
        Toast.makeText(this, "Register Successful", Toast.LENGTH_LONG).show();
        startActivity(i);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "System Failure", Toast.LENGTH_LONG).show();
    }
}