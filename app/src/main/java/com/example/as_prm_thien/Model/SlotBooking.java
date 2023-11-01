package com.example.as_prm_thien.Model;


import java.io.Serializable;

public class SlotBooking implements Serializable {

    private int slotId;

    private String startTime;

    private String endTime;

    private int price;

    private boolean booked;

    private String slotDate;


    public SlotBooking(int slotId, String startTime, String endTime, int price, boolean booked, String slotDate) {
        this.slotId = slotId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.booked = booked;
        this.slotDate = slotDate;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public String getSlotDate() {
        return slotDate;
    }

    public void setSlotDate(String slotDate) {
        this.slotDate = slotDate;
    }

    @Override
    public String toString() {
        return "SlotBooking{" +
                "slotId=" + slotId +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", price='" + price + '\'' +
                ", booked=" + booked +
                ", slotDate='" + slotDate + '\'' +
                '}';
    }
}
