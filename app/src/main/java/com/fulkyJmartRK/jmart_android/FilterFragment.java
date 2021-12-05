package com.fulkyJmartRK.jmart_android;

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
import com.fulkyJmartRK.jmart_android.request.LoginRequest;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FilterFragment} factory method to
 * create an instance of this fragment.
 */
public class FilterFragment extends Fragment {

    androidx.appcompat.widget.AppCompatButton clear;
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
        name = view.findViewById(R.id.name_filter_form);
        lowestPrice = view.findViewById(R.id.range_lowest);
        highestPrice = view.findViewById(R.id.range_highest);
        newFilter = view.findViewById(R.id.checkbox_new);
        used = view.findViewById(R.id.checkbox_used);
        category = view.findViewById(R.id.category_spinner);


        clear.setOnClickListener(this::onClearClick);
    }

    private void onClearClick(View view){
        name.setText("");
        lowestPrice.setText("");
        highestPrice.setText("");
        newFilter.setChecked(false);
        used.setChecked(false);
        category.setSelection(0);
    }
}