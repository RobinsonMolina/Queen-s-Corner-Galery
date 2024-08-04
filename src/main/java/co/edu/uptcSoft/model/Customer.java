package co.edu.uptcSoft.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Customer implements Comparable<Customer> {

    @Expose private String name;
    @Expose private long documentNumber;
    @Expose private String email;
    @Expose private String address;
    @Expose private long phoneNumber;
    private TreeSet<Order> orders;

    public Customer() {
        orders = new TreeSet<>();
    }

    public Customer(String name, long documentNumber, String email, String address, long phoneNumber) {
        this.name = name;
        this.documentNumber = documentNumber;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.orders = new TreeSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(long documentNumber) {
        this.documentNumber = documentNumber;
    }

    public TreeSet<Order> getOrders() {
        return orders;
    }

    public void setOrders(TreeSet<Order> orders) {this.orders = orders;}

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    @Override
    public int compareTo(Customer o) {
        return Long.compare(this.documentNumber, o.documentNumber);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", documentNumber=" + documentNumber +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
