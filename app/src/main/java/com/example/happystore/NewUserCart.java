package com.example.happystore;

import com.google.firebase.database.Exclude;

public class NewUserCart {
    private String name;
    private String price;
    private String quantity;
    private String mKey;
    private String TPrice;
    private String user;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
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

    @Exclude
    public String getmKey() {
        return mKey;
    }

    @Exclude
    public void setmKey(String mKey) {
        this.mKey = mKey;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
