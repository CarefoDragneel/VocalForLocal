package com.example.vocalforlocal.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.vocalforlocal.AddNewProductActivity;
import com.example.vocalforlocal.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class MyProductsFragment extends Fragment {

    public MyProductsFragment() {
        // Required empty public constructor
    }


    // creating hooks
    private Button mAddNewProductButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_my_products, container, false);

        // adding functionality to the hook
        mAddNewProductButton = (Button) rootView.findViewById(R.id.add_product_button);

        mAddNewProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AddNewProductActivity.class);
                startActivity(i);
            }
        });

        return rootView;
    }
}