package com.example.vocalforlocal.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.vocalforlocal.AddNewProductActivity;
import com.example.vocalforlocal.R;
import com.example.vocalforlocal.adapter.ProductAdapter;
import com.example.vocalforlocal.adapter.Products;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MyProductsFragment extends Fragment {

    public MyProductsFragment() {
        // Required empty public constructor
    }

    // creating hooks
    private Button mAddNewProductButton;

    //creating Adapter
    private ProductAdapter mAdapter;

    //adding database and its reference
    private FirebaseDatabase mDatabase;
    private DatabaseReference mProductReference;

    //defining value event listener
    private ValueEventListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_my_products, container, false);

        // adding functionality to the hook
        mAddNewProductButton = (Button) rootView.findViewById(R.id.add_product_button);
        ListView productListView = (ListView) rootView.findViewById(R.id.product_list);

        // setting database and reference
        mDatabase = FirebaseDatabase.getInstance();
        mProductReference = mDatabase.getReference().child("Products");

        // adding functionality to the button
        mAddNewProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AddNewProductActivity.class);
                startActivity(i);
            }
        });

        //creating adapter
        ArrayList<Products> list = new ArrayList<>();
        mAdapter = new ProductAdapter(getActivity().getApplicationContext(),list);
        productListView.setAdapter(mAdapter);

        // adding functionality to Value Event Listener
        mListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    mAdapter.add(ds.getValue(Products.class));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("MyProductFragment","There is an error in the Listener");
            }
        };
        mProductReference.addValueEventListener(mListener);

        return rootView;
    }
}