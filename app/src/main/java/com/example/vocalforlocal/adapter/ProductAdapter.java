package com.example.vocalforlocal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.vocalforlocal.R;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Products> {
    public ProductAdapter(@NonNull Context context, int resource, @NonNull List<Products> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // setting the layout of each list items in the listView
        View listview = convertView;
        if(listview == null){
            listview = LayoutInflater.from(getContext()).inflate(R.layout.product_list_items,parent,false);
        }




        return listview;
    }
}
