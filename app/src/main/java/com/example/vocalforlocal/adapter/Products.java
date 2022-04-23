package com.example.vocalforlocal.adapter;

public class Products {

    // item name
    private String product_item_name="";

    // item description
    private String product_item_description="";

    // item photo
    private String product_item_photo_url="";

    // item category
    private int product_item_category=0;

    //item price
    private int product_item_price;

    //item location: latitude and longitude
    private int product_item_latitude;
    private int product_item_longitude;

    // categories int value
    public final static int CLOTHING = 0;
    public final static int ELECTRONICS = 1;
    public final static int BEAUTY_PRODUCTS = 2;
    public final static int FOOTWEAR = 3;
    public final static int SPORTS = 4;

    public Products(){}

    public Products(String n,int c,String d,String p){
        product_item_name = n;
        product_item_category = c;
        product_item_description = d;
        product_item_photo_url = p;
    }

    public String getProduct_item_name() {
        return product_item_name;
    }

    public String getProduct_item_description() {
        return product_item_description;
    }

    public String getProduct_item_photo() {
        return product_item_photo_url;
    }

    public int getProduct_item_category() {
        return product_item_category;
    }
}
