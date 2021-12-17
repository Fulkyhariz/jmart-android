package com.fulkyJmartRK.jmart_android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.fulkyJmartRK.jmart_android.model.Filter;
import com.fulkyJmartRK.jmart_android.model.Product;
import com.fulkyJmartRK.jmart_android.model.ProductCategory;
import com.fulkyJmartRK.jmart_android.request.LoginRequest;

import java.io.Serializable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FilterFragment} factory method to
 * create an instance of this fragment.
 */
public class FilterFragment extends Fragment {

    /*SendMessage SM;*/

    String TAG_MY_CLASS = "";

    androidx.appcompat.widget.AppCompatButton clear;
    androidx.appcompat.widget.AppCompatButton apply;
    EditText name;
    EditText lowestPrice;
    EditText highestPrice;
    CheckBox newFilter;
    CheckBox used;
    Spinner category;

    public FilterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_filter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        clear = view.findViewById(R.id.clear_button);
        apply = view.findViewById(R.id.apply_button);
        name = view.findViewById(R.id.name_filter_form);
        lowestPrice = view.findViewById(R.id.range_lowest);
        highestPrice = view.findViewById(R.id.range_highest);
        newFilter = view.findViewById(R.id.checkbox_new);
        used = view.findViewById(R.id.checkbox_used);
        category = view.findViewById(R.id.category_spinner);

        if (Filter.isFiltered){
            name.setText(Filter.name);
            lowestPrice.setText(String.valueOf(Filter.minPrice));
            highestPrice.setText(String.valueOf(Filter.maxPrice));
            newFilter.setChecked(Filter.isUsed);
            used.setChecked(Filter.isNew);
            category.setSelection(Filter.categoryPos);
        }

        clear.setOnClickListener(this::onClearClick);
        apply.setOnClickListener(this::onApplyClick);
    }

    private void onClearClick(View view){
        name.setText("");
        lowestPrice.setText("");
        highestPrice.setText("");
        newFilter.setChecked(false);
        used.setChecked(false);
        category.setSelection(0);
        Filter.isFiltered = false;
    }
    private void onApplyClick(View view){

        if (name.getText() == null) Filter.name = "";
        else Filter.name = name.getText().toString();

        Filter.minPrice = Double.parseDouble(lowestPrice.getText().toString());
        Filter.maxPrice = Double.parseDouble(highestPrice.getText().toString());

        Filter.isNew = newFilter.isChecked();
        Filter.isUsed = used.isChecked();

        if (category.getSelectedItem().toString().equals("-")) Filter.category = ProductCategory.NOT_CATEGORY;
        else Filter.category = ProductCategory.valueOf(category.getSelectedItem().toString());

        Filter.categoryPos = category.getSelectedItemPosition();
        Filter.accountId = MainActivity.id;
        Filter.isFiltered = true;
    }
}