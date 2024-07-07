package co.edu.uptcSoft.model;

import java.util.Date;

public class Order implements Comparable<Order> {

    private String productName;
    private String status;
    private int orderNumber;
    private String type;
    private Date productionDate;
    private Date deliveryDate;
    private Customer customer;

    public Order() {
    }

    public Order(String productName, String status, int orderNumber, String type, Date productionDate, Date deliveryDate, Customer customer) {
        this.productName = productName;
        this.status = status;
        this.orderNumber = orderNumber;
        this.type = type;
        this.productionDate = productionDate;
        this.deliveryDate = deliveryDate;
        this.customer = customer;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNumber=" + orderNumber +
                ", customer=" + customer.getName() +
                '}';
    }

    @Override
    public int compareTo(Order o) {
        return Integer.compare(this.orderNumber, o.orderNumber);
    }
}
