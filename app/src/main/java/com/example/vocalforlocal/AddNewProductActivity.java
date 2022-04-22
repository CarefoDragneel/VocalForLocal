package com.example.vocalforlocal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import frlgrd.animatededittext.AnimatedEditText;

public class AddNewProductActivity extends AppCompatActivity {

    // defining hooks
    private AnimatedEditText mProductItemName;
    private AnimatedEditText mProductItemDescription;
    private Spinner mSelectCategory;
    private TextView mPhotoPicker;
    private Button mSaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_product_layout);

        //providing functionality to the hooks
        mProductItemName = (AnimatedEditText) findViewById(R.id.product_item_name);
        mProductItemDescription = (AnimatedEditText) findViewById(R.id.product_item_description);
        mPhotoPicker = (TextView) findViewById(R.id.product_item_image);
        mSaveButton = (Button) findViewById(R.id.product_item_save_button);



    }
}