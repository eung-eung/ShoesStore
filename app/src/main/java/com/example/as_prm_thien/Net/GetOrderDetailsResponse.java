package com.example.as_prm_thien.Net;

public class GetOrderDetailsResponse {
    private int id;
    private int order_id;
    private int product_id;
    private int at_price;
    private int quantity;
    private String created_at;
    private String updated_at;

    public GetOrderDetailsResponse(int id, int order_id, int product_id, int at_price, int quantity, String created_at, String updated_at) {
        this.id = id;
        this.order_id = order_id;
        this.product_id = product_id;
        this.at_price = at_price;
        this.quantity = quantity;
        this.created_at = created_at;
        this.updated_at = updated_at;
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

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getAt_price() {
        return at_price;
    }

    public void setAt_price(int at_price) {
        this.at_price = at_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return String.format("ID: %d, OrderID: %d, ProductID: %d, AtPrice: %d, Quantity: %d, CreatedAt: %s, UpdatedAt: %s", id, order_id, product_id, at_price, quantity, created_at, updated_at);
    }
}
