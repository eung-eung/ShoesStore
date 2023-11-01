package com.example.as_prm_thien.Model;


import java.io.Serializable;

public class OrderDetail implements Serializable {

    private int OrderDetailId;
    private int price;
    private String productName;
    private int itemCount;

    private String avatarStudio;

    private String coverImage;


    public OrderDetail(int orderDetailId, int price, String productName, int itemCount, String avatarStudio, String coverImage) {
        OrderDetailId = orderDetailId;
        this.price = price;
        this.productName = productName;
        this.itemCount = itemCount;
        this.avatarStudio = avatarStudio;
        this.coverImage = coverImage;
    }

    public int getOrderDetailId() {
        return OrderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        OrderDetailId = orderDetailId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }


    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getAvatarStudio() {
        return avatarStudio;
    }

    public void setAvatarStudio(String avatarStudio) {
        this.avatarStudio = avatarStudio;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "OrderDetailId=" + OrderDetailId +
                ", price=" + price +
                ", productName='" + productName + '\'' +
                ", itemCount=" + itemCount +
                ", avatarStudio='" + avatarStudio + '\'' +
                ", coverImage='" + coverImage + '\'' +
                '}';
    }
}
