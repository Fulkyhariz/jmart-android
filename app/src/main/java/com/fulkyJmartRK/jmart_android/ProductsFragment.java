package com.fulkyJmartRK.jmart_android;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.fulkyJmartRK.jmart_android.R;
import com.fulkyJmartRK.jmart_android.LoginActivity;
import com.fulkyJmartRK.jmart_android.adapter.ProductsAdapter;
import com.fulkyJmartRK.jmart_android.model.Account;
import com.fulkyJmartRK.jmart_android.model.Product;
import com.fulkyJmartRK.jmart_android.model.Serializable;
import com.fulkyJmartRK.jmart_android.model.Store;
import com.fulkyJmartRK.jmart_android.request.GetProductRequest;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductsFragment} factory method to
 * create an instance of this fragment.
 */
public class ProductsFragment extends Fragment implements Response.Listener<String>, Response.ErrorListener{

    ArrayList<Product> products ;
    ListView productList;
    EditText page;
    Account loggedAccount;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_products, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        productList = view.findViewById(R.id.list_product);
        loggedAccount = LoginActivity.getLoggedAccount();

        GetProductRequest req = new GetProductRequest(/*1, 0,*/this, this);
        System.out.println("this line also works");
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(req);
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
                    System.out.println(product.name);
                    products.add(product);
                }
                ProductsAdapter adapter = new ProductsAdapter(getContext(), products);
                productList.setAdapter(adapter);
            }
        }
        catch(JSONException e){
            e.printStackTrace();
        }
    }
}