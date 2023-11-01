package com.example.as_prm_thien.Model;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {

    private int orderId;

    private String orderDate;

    private List<OrderDetail> orderDetail;

    public Order(int orderId, String orderDate, List<OrderDetail> orderDetail) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderDetail = orderDetail;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }


    public List<OrderDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }


    @Override
    public String toString() {
        return "OrderInformation{" +
                "orderId=" + orderId +
                ", orderDate='" + orderDate + '\'' +
                ", orderDetail=" + orderDetail +
                '}';
    }
}
