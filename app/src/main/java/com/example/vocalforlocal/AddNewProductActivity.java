package com.example.vocalforlocal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vocalforlocal.adapter.Products;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import frlgrd.animatededittext.AnimatedEditText;

public class AddNewProductActivity extends AppCompatActivity {

    // defining hooks
    private EditText mProductItemName;
    private EditText mProductItemDescription;
    private Spinner mSelectCategory;
    private TextView mPhotoPicker;
    private Button mSaveButton;
    private ImageView mPreviewImage;

    //variable to store value of category
    private int mProductCategory;

    // temporarily we create an anonymous user
    public static final String USER = "Anonymous";

    // adding realtime firebase database
    FirebaseDatabase mDatabase;
    // creating database reference to store the name, description and category
    DatabaseReference mProductsReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_product_layout);

        //providing functionality to the hooks
        mProductItemName = (EditText) findViewById(R.id.product_item_name);
        mProductItemDescription = (EditText) findViewById(R.id.product_item_description);
        mPhotoPicker = (TextView) findViewById(R.id.product_item_image);
        mSaveButton = (Button) findViewById(R.id.product_item_save_button);
        mSelectCategory = (Spinner) findViewById(R.id.product_item_category);
        mPreviewImage = (ImageView) findViewById(R.id.preview_image);

        // adding functionality to the database and its reference
        mDatabase = FirebaseDatabase.getInstance();
        mProductsReference = mDatabase.getReference().child("Products");

        //adding functionality to mSaveButton
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // creating Products objects
                Products product = new Products(mProductItemName.getText().toString(),mProductCategory,mProductItemDescription.getText().toString(),null);
                // entering these values in database
                mProductsReference.push().setValue(product);
                finish();
            }
        });




        // setting up the spinner
        setupSpinner();

        // adding some empty photo to preview image view
        if(mPreviewImage.getDrawable()==null){
            mPreviewImage.setImageResource(R.drawable.folder_icon);
        }

        // setting photo picker to the text view
        mPhotoPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // this is an image picker library
                ImagePicker.with(AddNewProductActivity.this)
                        .crop(3f,2f)	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            Uri uri = data.getData();
            // Use Uri object instead of File to avoid storage permissions
            mPreviewImage.setImageURI(uri);
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
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