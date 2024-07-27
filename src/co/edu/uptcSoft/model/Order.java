package co.edu.uptcSoft.model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.Date;

public class Order implements Comparable<Order> {

    @Expose private String productName;
    @Expose private String status;
    @Expose private int orderNumber;
    @Expose private String type;
    @Expose private Date productionDate;
    @Expose private Date deliveryDate;
    @Expose private Customer customer;
    @Expose private ArrayList<Materials> materials;

    public Order() {
    }

    public Order(String productName, String status, int orderNumber, String type, Date productionDate, Date deliveryDate, Customer customer, ArrayList<Materials> materials) {
        this.productName = productName;
        this.status = status;
        this.orderNumber = orderNumber;
        this.type = type;
        this.productionDate = productionDate;
        this.deliveryDate = deliveryDate;
        this.customer = customer;
        this.materials = materials;
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

    public ArrayList<Materials> getMaterials() {
        return materials;
    }

    public void setMaterials(ArrayList<Materials> materials) {
        this.materials = materials;
    }

    @Override
    public int compareTo(Order o) {
        return Integer.compare(this.orderNumber, o.orderNumber);
    }

    @Override
    public String toString() {
        return "Order{" +
                "productName='" + productName + '\'' +
                ", status='" + status + '\'' +
                ", orderNumber=" + orderNumber +
                ", type='" + type + '\'' +
                ", productionDate=" + productionDate +
                ", deliveryDate=" + deliveryDate +
                ", customer=" + customer +
                ", materials=" + materials +
                '}';
    }
}
