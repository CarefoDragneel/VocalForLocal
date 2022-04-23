package com.example.vocalforlocal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.vocalforlocal.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends ArrayAdapter<Products> {

    //hooks
    private TextView mProductNameView;
    private TextView mCategoryView;


    //defining array adapter
    ProductAdapter mProductAdapter;

    // constructor
    public ProductAdapter(@NonNull Context context, @NonNull List<Products> objects) {
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

        // setting hooks
        mProductNameView = listview.findViewById(R.id.item_show_name);
        mCategoryView = listview.findViewById(R.id.item_show_category);

        //receive each item
        Products productItem = getItem(position);

        // extracting string from spinner
        String category = extractStringFromSpinner(productItem.getProduct_item_category());

        //setting texts in various views
        mProductNameView.setText(productItem.getProduct_item_name());
        mCategoryView.setText("CATEGORY: "+category);

        return listview;
    }

    private String extractStringFromSpinner(int category_no){
        switch (category_no){
            case 0:
                return "Clothing";
            case 1:
                return "Electronics";
            case 2:
                return "Beauty Products";
            case 3:
                return "Footwear";
            default:
                return "Sports";
        }
    }

}
