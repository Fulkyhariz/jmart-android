package com.fulkyJmartRK.jmart_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.fulkyJmartRK.jmart_android.R;
import com.fulkyJmartRK.jmart_android.model.Product;

import java.util.ArrayList;

public class ProductsAdapter extends ArrayAdapter<Product> {



    public ProductsAdapter(Context context, ArrayList<Product> product){
        super(context, 0, product);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        Product product = getItem(position);

        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_product, parent, false);
        }

        TextView nameProduct =  (TextView) convertView.findViewById(R.id.item_product);

        nameProduct.setText(product.name);

        return convertView;
    }
}