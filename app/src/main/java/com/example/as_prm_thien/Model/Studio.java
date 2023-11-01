package com.example.as_prm_thien.Model;


import java.io.Serializable;

public class Studio implements Serializable {

    private int studioId;

    private String name;

    private String address;

    private String description;

    private Double totalRating;

    private String createDate;

    private String avatarStudio;

    private String coverImage;
    private int price;
    private boolean deleted;


    public Studio(int studioId, String name, String address, String description, Double totalRating, String createDate, String avatarStudio, String coverImage, int price, boolean deleted) {
        this.studioId = studioId;
        this.name = name;
        this.address = address;
        this.description = description;
        this.totalRating = totalRating;
        this.createDate = createDate;
        this.avatarStudio = avatarStudio;
        this.coverImage = coverImage;
        this.price = price;
        this.deleted = deleted;
    }

    public int getStudioId() {
        return studioId;
    }

    public void setStudioId(int studioId) {
        this.studioId = studioId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(Double totalRating) {
        this.totalRating = totalRating;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getAvatarStudio() {
        return avatarStudio;
    }

    public void setAvatarStudio(String avatarStudio) {
        this.avatarStudio = avatarStudio;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setStatus(boolean status) {
        this.deleted = status;
    }

    @Override
    public String toString() {
        return "Studio{" +
                "studioId=" + studioId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", totalRating=" + totalRating +
                ", createDate='" + createDate + '\'' +
                ", avatarStudio='" + avatarStudio + '\'' +
                ", coverImage='" + coverImage + '\'' +
                ", deleted=" + deleted +
                '}';
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
