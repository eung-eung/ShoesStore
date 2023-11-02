package com.example.as_prm_thien.Net;

public class LoginUserVerifyResponse {
    private String access_token;
    private String email;
    private String phone;
    private String name;
    private String avatar_url;
    private int balance;

    public LoginUserVerifyResponse(String access_token, String email, String phone, String name, String avatar_url, int balance) {
        this.access_token = access_token;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.avatar_url = avatar_url;
        this.balance = balance;
    }

    public String getAccessToken() {
        return access_token;
    }

    public void setAccessToken(String accessToken) {
        this.access_token = accessToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatar_url;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatar_url = avatarUrl;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return String.format("Access token: %s, name: %s, email: %s, phone: %s, avatar_url: %s", access_token, name, email, phone, avatar_url);
    }
}
