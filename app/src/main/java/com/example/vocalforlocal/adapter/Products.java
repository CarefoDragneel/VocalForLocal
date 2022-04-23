package com.example.vocalforlocal.adapter;

public class Products {

    // item name
    private String product_item_name;

    // item description
    private String product_item_description;

    // item photo
    private int product_item_photo;

    // item category
    private int product_item_category;

    // categories int value
    public final static int CLOTHING = 0;
    public final static int ELECTRONICS = 1;
    public final static int BEAUTY_PRODUCTS = 2;
    public final static int FOOTWEAR = 3;
    public final static int SPORTS = 4;

    public Products(String n,String d,int p){
        product_item_name = n;
        product_item_description = d;
        product_item_photo = p;
    }

    public String getProduct_item_name() {
        return product_item_name;
    }

    public String getProduct_item_description() {
        return product_item_description;
    }

    public int getProduct_item_photo() {
        return product_item_photo;
    }


}
