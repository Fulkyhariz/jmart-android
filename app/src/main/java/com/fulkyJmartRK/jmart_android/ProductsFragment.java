package com.fulkyJmartRK.jmart_android;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.fulkyJmartRK.jmart_android.adapter.ProductsAdapter;
import com.fulkyJmartRK.jmart_android.model.Account;
import com.fulkyJmartRK.jmart_android.model.Product;
import com.fulkyJmartRK.jmart_android.model.Filter;
import com.fulkyJmartRK.jmart_android.request.RequestFactory;

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
    Integer halaman;
    androidx.appcompat.widget.AppCompatButton goBtn;
    androidx.appcompat.widget.AppCompatButton prevBtn;
    androidx.appcompat.widget.AppCompatButton nextBtn;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_products, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        page = view.findViewById(R.id.page);
        goBtn = view.findViewById(R.id.go_button);
        nextBtn = view.findViewById(R.id.next_button);
        prevBtn = view.findViewById(R.id.prev_button);
        productList = view.findViewById(R.id.list_product);
        loggedAccount = LoginActivity.getLoggedAccount();
        halaman = 0;

        requestList();

        System.out.println(Filter.name);

        goBtn.setOnClickListener(this::onGoClicked);
        nextBtn.setOnClickListener(this::onNextClicked);
        prevBtn.setOnClickListener(this::onPrevClicked);
        productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Bundle bundle = new Bundle();
                Intent i = new Intent(getContext(), ItemDetailsActivity.class);
                bundle.putSerializable("item", new Product(products.get(position).accountId, products.get(position).id, products.get(position).category,
                        products.get(position).conditionUsed, products.get(position).discount, products.get(position).name,
                        products.get(position).price, products.get(position).shipmentPlans, products.get(position).weight));
                i.putExtras(bundle);
                startActivity(i);
            }
        });
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println(Filter.name);
        requestList();
    }

    public void onGoClicked(View view) {
        halaman = Integer.parseInt(page.getText().toString()) - 1;
        requestList();
    }

    public void onNextClicked(View view) {
        halaman += 1;
        requestList();
    }

    public void onPrevClicked(View view) {
        halaman -= 1;
        if (halaman < 0) halaman = 0;
        requestList();
    }

    private void requestList(){
        if (!Filter.isFiltered){
            RequestQueue queue = Volley.newRequestQueue(getContext());
            queue.add(RequestFactory.getPage("product", halaman, 10, this, this));
        } else{
            RequestQueue queue = Volley.newRequestQueue(getContext());
            queue.add(RequestFactory.getPageFiltered(halaman, 10, Filter.name, Filter.minPrice, Filter.maxPrice
            ,Filter.isUsed, Filter.isNew, Filter.category, this, this));
        }
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
                ProductsAdapter adapter = new ProductsAdapter(getContext(), products);
                productList.setAdapter(adapter);
            }
        }
        catch(JSONException e){
            e.printStackTrace();
        }
    }

}