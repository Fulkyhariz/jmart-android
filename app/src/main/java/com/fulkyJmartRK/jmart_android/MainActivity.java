package com.fulkyJmartRK.jmart_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.fulkyJmartRK.jmart_android.model.Account;
import com.fulkyJmartRK.jmart_android.request.RequestFactory;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements Response.Listener<String>, Response.ErrorListener{

    private static final Gson gson = new Gson();

    public static int id;
    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapter adapter;
    public static Account loggedAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tab_layout);
        pager2 = findViewById(R.id.view_pager2);

        loggedAccount = LoginActivity.getLoggedAccount();
        id = getIntent().getIntExtra("id", 0);

        FragmentManager fm = getSupportFragmentManager();
        adapter = new FragmentAdapter(fm, getLifecycle());
        pager2.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setText("PRODUCT"));
        tabLayout.addTab(tabLayout.newTab().setText("FILTER"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem add = menu.findItem(R.id.menu_add);

        if(loggedAccount.store == null){
            add.setVisible(false);
        }
        else{
            add.setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                onAddClick();
                return true;
            case R.id.menu_search:

                return true;
            case R.id.menu_profile:
                onProfileClick();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onAddClick(){
        Intent i = new Intent(this, CreateProductActivity.class);
        startActivity(i);
    }

    private void onProfileClick(){
        Intent i = new Intent(this, AboutMeActivity.class);
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(RequestFactory.getById("account", id,this, this));
    }

    @Override
    public void onResponse(String response){
        try{
            JSONObject obj = new JSONObject(response);
            loggedAccount = gson.fromJson(obj.toString(), Account.class);
        } catch (Exception e) {
            return;
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    /*    @Override
    public void sendData(Filter message) {
        String tag = "android:switcher:" + R.id.view_pager2 + ":" + 0;
        ProductsFragment f = (ProductsFragment) getSupportFragmentManager().findFragmentByTag(tag);
        f.setFilter(message);
    }*/
}