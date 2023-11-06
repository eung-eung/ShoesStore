package com.example.as_prm_thien.Net;

public class GetOrderOfUserResponse {
    private int id;
    private int user_id;
    private int payment_status;
    private String created_at;
    private String updated_at;

    public GetOrderOfUserResponse(int id, int user_id, int payment_status, String created_at, String updated_at) {
        this.id = id;
        this.user_id = user_id;
        this.payment_status = payment_status;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(int payment_status) {
        this.payment_status = payment_status;
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
        return String.format("ID: %d, UserID: %d, PaymentStatus: %d, CreatedAt: %s, UpdatedAt: %s", id, user_id, payment_status, created_at, updated_at);
    }
}
