package co.edu.uptcSoft.logic;

import co.edu.uptcSoft.model.Customer;
import co.edu.uptcSoft.model.Materials;
import co.edu.uptcSoft.model.Order;

import java.util.*;

public class Logic {

    private static Logic instance; // Single instance of Logic
    private TreeMap<Long, Customer> customerList;
    private TreeMap<Integer, Order> orderList;
    private Order order;
    private Customer customer;

    private Logic() {
        customerList = new TreeMap<>();
        orderList = new TreeMap<>();
        customer = new Customer();
        loadCustomers();
        loadOrders();
    }

    // Static method to get the single instance of Logic
    public static Logic getInstance() {
        if (instance == null) {
            instance = new Logic();
        }
        return instance;
    }

    // Method to load customers
    private void loadCustomers() {
        addCustomer("Carlos", 12345, "carlos@gmail.com", "A", 12345);
    }

    // Method to load orders
    private void loadOrders() {
        addOrder("Producto", "Pendiente", 1, "Pedido", new Date(), new Date(), "Andre", 12345,  123l, null);
        addOrder("Producto", "Pendiente", 2, "Pedido", new Date(), new Date(), "Carlos", 12345,  123l, null);
        addOrder("Producto", "Pendiente", 2, "Pedido", new Date(), new Date(), "Felipe", 12345,  123l, null);
        addOrder("Producto", "Pendiente", 3, "Pedido", new Date(), new Date(), "Felipe", 12345,  123l, null);
        addOrder("Producto", "Pendiente", 4, "Pedido", new Date(), new Date(), "Juan", 12345,  7890l, null);
        addOrder("Producto", "Pendiente", 5, "Pedido", new Date(), new Date(), "esteban", 12345,7890l, null);
    }

    // Method to add customers
    public void addCustomer(String name, long documentNumber, String email, String address, long phoneNumber) {
        customerList.put(documentNumber, new Customer(name, documentNumber, email, address, phoneNumber));
    }

    // Method to add orders
    public void addOrder(String productName, String status, int orderNumber, String type, Date productionDate,
                         Date deliveryDate, String name,long phoneNumber,
                         long documentNumber, ArrayList<Materials> materials) {

        order = new Order(productName, status, orderNumber, type, productionDate, deliveryDate, null, null);

        if (!customerList.containsKey(documentNumber)) { // If the customer does not exist
            addCustomer(name, documentNumber, "", "", phoneNumber);
            customerList.get(documentNumber).addOrder(order);
            order.setCustomer(customerList.get(documentNumber));
        } else { // If the customer already exists
             customer = customerList.get(documentNumber);
             customer.setName(name);
             customer.setDocumentNumber(documentNumber);
             customer.setPhoneNumber(phoneNumber);
             order.setCustomer(customer);
             customer.addOrder(order);
        }

        orderList.put(orderNumber, order);
    }

    public TreeMap<Integer, Order> getOrderList() {
        return orderList;
    }

    public TreeMap<Long, Customer> getCustomerList() {
        return customerList;
    }

    public void deleteOrder(long row) {
        orderList.remove((int) row);
    }
}
