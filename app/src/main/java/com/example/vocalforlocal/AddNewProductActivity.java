package com.example.vocalforlocal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.vocalforlocal.adapter.Products;

import frlgrd.animatededittext.AnimatedEditText;

public class AddNewProductActivity extends AppCompatActivity {

    // defining hooks
    private AnimatedEditText mProductItemName;
    private AnimatedEditText mProductItemDescription;
    private Spinner mSelectCategory;
    private TextView mPhotoPicker;
    private Button mSaveButton;

    //variable to store value of category
    int mProductCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_product_layout);

        //providing functionality to the hooks
        mProductItemName = (AnimatedEditText) findViewById(R.id.product_item_name);
        mProductItemDescription = (AnimatedEditText) findViewById(R.id.product_item_description);
        mPhotoPicker = (TextView) findViewById(R.id.product_item_image);
        mSaveButton = (Button) findViewById(R.id.product_item_save_button);
        mSelectCategory = (Spinner) findViewById(R.id.product_item_category);

        // setting up the spinner
        setupSpinner();

        // setting photo picker to the text view


    }

    // this method is used to setup layout of spinner
    public void setupSpinner(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.array_category_options, android.R.layout.simple_spinner_item);
        /*
         * this method is used to set a default layout to the drop down menu
         */
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        /*
         * here we attach the adapter to the spinner
         */
        mSelectCategory.setAdapter(adapter);
        /*
         * here we set the event listener to the spinner
         */
        mSelectCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /*
            overriding the method which specify if any option in the menu is selected
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /*
                 * we extract string from the adapter view by getting items at each position nad
                 * below we set each value on each selection
                 */
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.Clothing))) {
                        mProductCategory = Products.CLOTHING;
                    }
                    else if (selection.equals(getString(R.string.Electronics))) {
                        mProductCategory = Products.ELECTRONICS;
                    }
                     else if (selection.equals(getString(R.string.Beauty_Products))) {
                        mProductCategory = Products.BEAUTY_PRODUCTS;
                    }
                     else if (selection.equals(getString(R.string.Footwear))) {
                        mProductCategory = Products.FOOTWEAR;
                    }
                    else {
                        mProductCategory = Products.SPORTS;
                    }
                }
            }
            /*
             * this specifies if nothing is selected
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mProductCategory = Products.CLOTHING;
            }
        });
    }
}