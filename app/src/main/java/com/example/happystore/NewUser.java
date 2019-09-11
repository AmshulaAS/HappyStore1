package com.example.happystore;

import android.widget.EditText;
import android.widget.TextView;

public class NewUser {
    private String name;
    private String price;
    private String quantity;
    private String TPrice;
    private String mKey;
    private String user;



    public NewUser(String name, String price, String quantity,String TPrice,String user) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.TPrice=TPrice;
        this.user=user;
    }

    public NewUser(TextView name, TextView price, EditText quantity, TextView Tprice) {
        this.name = name.getText().toString();
        this.price = price.getText().toString();
        this.quantity = quantity.getText().toString();
        this.TPrice = Tprice.getText().toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTPrice() {
        return TPrice;
    }

    public void setTPrice(String TPrice) {
        this.TPrice = TPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        quantity = quantity;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
