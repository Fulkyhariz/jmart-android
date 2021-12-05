package com.fulkyJmartRK.jmart_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.*;
import android.widget.TextView;
import android.widget.Toast;

import com.fulkyJmartRK.jmart_android.model.*;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapter adapter;
    Account loggedAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tab_layout);
        pager2 = findViewById(R.id.view_pager2);

        loggedAccount = LoginActivity.getLoggedAccount();

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
                Toast.makeText(this, "add selected", Toast.LENGTH_LONG).show();
                onAddClick();
                return true;
            case R.id.menu_search:

                return true;
            case R.id.menu_profile:
                Toast.makeText(this, "profile selected", Toast.LENGTH_LONG).show();
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
}