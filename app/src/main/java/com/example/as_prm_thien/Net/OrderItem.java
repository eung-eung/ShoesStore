package com.example.as_prm_thien.Net;

public class OrderItem {
    private int id;
    private int order_id;
    private int product_id;
    private int quantity;
    private int at_price;

    public OrderItem(int product_id, int quantity, int at_price) {
        this.product_id = product_id;
        this.quantity = quantity;
        this.at_price = at_price;
    }

    public OrderItem(int id, int order_id, int product_id, int quantity, int at_price) {
        this.id = id;
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.at_price = at_price;
    }


    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAt_price() {
        return at_price;
    }

    public void setAt_price(int at_price) {
        this.at_price = at_price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
}
