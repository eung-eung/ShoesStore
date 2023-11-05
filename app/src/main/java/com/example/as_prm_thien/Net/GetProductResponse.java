package com.example.as_prm_thien.Net;

public class GetProductResponse {
    private int id;
    private String name;
    private String description;
    private int price;
    private String image_url;

    public GetProductResponse(int id, String name, String description, int price, String image_url) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image_url = image_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    @Override
    public String toString() {
        return String.format("ID: %d, name: %s, description: %s, price: %d, image_url: %s", id, name, description, price, image_url);
    }
}
