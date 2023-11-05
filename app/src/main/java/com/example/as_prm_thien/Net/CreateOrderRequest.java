package com.example.as_prm_thien.Net;

import java.util.ArrayList;

public class CreateOrderRequest {
    private int user_id;
    private ArrayList<OrderItem> products;

    public CreateOrderRequest(int user_id, ArrayList<OrderItem> products) {
        this.user_id = user_id;
        this.products = products;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public ArrayList<OrderItem> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<OrderItem> products) {
        this.products = products;
    }
}
