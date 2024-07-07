package co.edu.uptcSoft.logic;

import co.edu.uptcSoft.model.Customer;
import co.edu.uptcSoft.model.Order;

import java.util.*;

public class Logic {

    private TreeMap<Long, Customer> customerList;
    private TreeMap<Integer, Order> orderList;
    private Order order;
    private Customer customer;
    private int orderNumber;

    public Logic() {
        customerList = new TreeMap<>();
        orderList = new TreeMap<>();
        customer = new Customer();
        loadCustomers();
        loadOrders();
    }

    private void loadCustomers() {
        addCustomer("Carlos", 12345, "carlos@gmail.com", "A", 12345);
    }

    private void loadOrders() {
        addOrder("Producto", "Pendiente", 1, "Pedido", new Date(), new Date(), "Andre", 12345,  123l);
        addOrder("Producto", "Pendiente", 2, "Pedido", new Date(), new Date(), "Carlos", 12345,  123l);
        addOrder("Producto", "Pendiente", 2, "Pedido", new Date(), new Date(), "Felipe", 12345,  123l);
        addOrder("Producto", "Pendiente", 3, "Pedido", new Date(), new Date(), "Felipe", 12345,  123l);
        addOrder("Producto", "Pendiente", 4, "Pedido", new Date(), new Date(), "Juan", 12345,  7890l);
        addOrder("Producto", "Pendiente", 5, "Pedido", new Date(), new Date(), "esteban", 12345,7890l);
    }

    public void addCustomer(String name, long documentNumber, String email, String address, long phoneNumber) {
        customerList.put(documentNumber, new Customer(name, documentNumber, email, address, phoneNumber));
    }

    public void addOrder(String productName, String status, int orderNumber, String type, Date productionDate,
                         Date deliveryDate, String name,long phoneNumber,
                         long documentNumber) {

        order = new Order(productName, status, orderNumber, type, productionDate, deliveryDate, null);

        if (!customerList.containsKey(documentNumber)) {
            System.out.println("No existe");
            addCustomer(name, documentNumber, "", "", phoneNumber);
            customerList.get(documentNumber).addOrder(order);
            order.setCustomer(customerList.get(documentNumber));
        } else {
            System.out.println("Existe");
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



    /*public static void main(String[] args) {
        logic l = new logic();

        l.addOrder("Producto", "Pendiente", 1, "Pedido", new Date(), new Date(), "Andre", 12345,  123l);
        l.addOrder("Producto", "Pendiente", 2, "Pedido", new Date(), new Date(), "Carlos", 12345,  123l);
        l.addOrder("Producto", "Pendiente", 2, "Pedido", new Date(), new Date(), "Felipe", 12345,  123l);
        l.addOrder("Producto", "Pendiente", 3, "Pedido", new Date(), new Date(), "Felipe", 12345,  123l);
        l.addOrder("Producto", "Pendiente", 4, "Pedido", new Date(), new Date(), "Juan", 12345,  7890l);
        l.addOrder("Producto", "Pendiente", 5, "Pedido", new Date(), new Date(), "esteban", 12345,7890l);

        System.out.println(l.orderList.size());
        for (Map.Entry<Integer, Order> entry : l.orderList.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        System.out.println("------------------------------------------");

        // Mostrar numero de documento, nombre y numero de orden
        for (Map.Entry<Long, Customer> entry : l.customerList.entrySet()) {
            //Cliente cliente = entry.getValue();
            System.out.print("Nombre: " + entry.getValue().getName() + " ");
            System.out.print(" Documento: " + entry.getValue().getDocumentNumber());
            System.out.print(" Correo: " + entry.getValue().getEmail() + " ");
            System.out.print(" Dirección: " + entry.getValue().getAddress() + " ");
            System.out.print(" Teléfono: " + entry.getValue().getPhoneNumber() + " ");

            System.out.print(" Órdenes: ");
            for (Order order : entry.getValue().getOrders()) {
                System.out.print(order.getOrderNumber() + ", ");
            }
            System.out.println();
        }
    }*/
}
