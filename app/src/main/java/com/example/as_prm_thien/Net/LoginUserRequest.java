package com.example.as_prm_thien.Net;

public class LoginUserRequest {
    private String email;

    public LoginUserRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
