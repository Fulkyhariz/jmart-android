package com.fulkyJmartRK.jmart_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.fulkyJmartRK.jmart_android.MainActivity;
import com.fulkyJmartRK.jmart_android.R;
import com.fulkyJmartRK.jmart_android.model.Payment;
import com.fulkyJmartRK.jmart_android.model.Product;
import com.fulkyJmartRK.jmart_android.request.RequestFactory;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class PaymentAdapter extends ArrayAdapter<Payment> implements Response.Listener<String>, Response.ErrorListener{

    ArrayList<Product> products ;

    TextView nameProduct;
    TextView date;
    TextView status;
    TextView total;

    Payment payment;

    public PaymentAdapter(Context context, ArrayList<Payment> payment){
        super(context, 0, payment);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        payment = getItem(position);

        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user_history, parent, false);
        }

        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(RequestFactory.getByIdStore("product", payment.productId, payment.buyerId,this, this));

        nameProduct =  (TextView) convertView.findViewById(R.id.name_user_detail);
        date =  (TextView) convertView.findViewById(R.id.date_user_detail);
        status =  (TextView) convertView.findViewById(R.id.status_user_detail);
        total =  (TextView) convertView.findViewById(R.id.total_user_detail);

        return convertView;
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(String response) {
        products = new ArrayList<>();
        try{
            JSONArray arr = new JSONArray(response);
            if(arr != null){
                for (int i = 0; i < arr.length(); i++){
                    Product product = new Product(arr.getJSONObject(i));
                    products.add(product);
                }
            }
            nameProduct.setText(products.get(0).name);
            date.setText(payment.date);
            status.setText(payment.status);
            total.setText(String.valueOf(products.get(0).price * payment.productCount));
        }
        catch(JSONException e){
            e.printStackTrace();
        }
    }
}
