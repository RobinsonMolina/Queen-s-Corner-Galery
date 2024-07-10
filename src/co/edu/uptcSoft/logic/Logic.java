package co.edu.uptcSoft.logic;

import co.edu.uptcSoft.model.Customer;
import co.edu.uptcSoft.model.Materials;
import co.edu.uptcSoft.model.Order;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            addOrder("SofaCama", "Por Hacer", 1, "Mueble", sdf.parse("10/06/2024"), sdf.parse("25/06/2024"), "Juan David Pérez", 3001234567L, 1234567890L, null);
            addOrder("Mesa De Centro", "En Proceso", 2, "Mueble", sdf.parse("12/06/2024"), sdf.parse("27/06/2024"), "María Alejandra Rodríguez", 339876543L, 2345678901L, null);
            addOrder("Cojines", "Por Hacer", 3, "Mueble", sdf.parse("15/06/2024"), sdf.parse("30/06/2024"), "Carlos Andrés Gómez", 323456789L, 3456789012L, null);
            addOrder("Lampara", "En Proceso", 4, "Mueble", sdf.parse("17/06/2024"), sdf.parse("02/07/2024"), "Luisa Fernanda Fernández", 3109876543L, 4567890123L, null);
            addOrder("Sofa", "Por Hacer", 5, "Mueble", sdf.parse("20/06/2024"), sdf.parse("05/07/2024"), "Andrés Felipe López", 316879801L, 5678901234L, null);
            addOrder("SofaCama", "En Proceso", 6, "Mueble", sdf.parse("22/06/2024"), sdf.parse("07/07/2024"), "Ana María Torres", 318678902L, 6789012345L, null);
            addOrder("SofaCama", "Por Hacer", 7, "Mueble", sdf.parse("25/06/2024"), sdf.parse("10/07/2024"), "Diego Alejandro Martínez", 317890123L, 7890123456L, null);
            addOrder("SofaCama", "En Proceso", 8, "Mueble", sdf.parse("27/06/2024"), sdf.parse("12/07/2024"), "Laura Patricia Ramírez", 3198901234L, 8901234567L, null);
            addOrder("Lampara", "Por Hacer", 9, "Mueble", sdf.parse("29/06/2024"), sdf.parse("14/07/2024"), "Pedro José Jiménez", 3109021345L, 9012345678L, null);
            addOrder("Mesa De Centro", "En Proceso", 10, "Mueble", sdf.parse("01/06/2024"), sdf.parse("16/06/2024"), "Sofía Margarita Morales", 3190173456L, 1234509876L, null);
            addOrder("Cojines", "Por Hacer", 11, "Mueble", sdf.parse("03/06/2024"), sdf.parse("18/06/2024"), "Jorge Luis Ríos", 3111234567L, 2345609876L, null);
            addOrder("Mesa De Centro", "En Proceso", 12, "Mueble", sdf.parse("05/06/2024"), sdf.parse("20/06/2024"), "María Alejandra Rodríguez", 3398763543L, 3456709876L, null);
            addOrder("Mesa De Centro", "Por Hacer", 13, "Mueble", sdf.parse("07/06/2024"), sdf.parse("22/06/2024"), "Alberto Gómez", 3209876534L, 4567809876L, null);
            addOrder("Mesa De Centro", "En Proceso", 14, "Mueble", sdf.parse("10/06/2024"), sdf.parse("24/06/2024"), "Santiago Herrera", 3123453678L, 5678909876L, null);
            addOrder("Mesa De Centro", "Por Hacer", 15, "Mueble", sdf.parse("11/06/2024"), sdf.parse("26/06/2024"), "Ricardo Pérez", 3219876534L, 6789010987L, null);
            addOrder("Mesa De Centro", "En Proceso", 16, "Mueble", sdf.parse("13/06/2024"), sdf.parse("28/06/2024"), "Daniela Torres", 3245678390L, 7890120987L, null);
            addOrder("Mesa De Centro", "Por Hacer", 17, "Mueble", sdf.parse("15/06/2024"), sdf.parse("30/06/2024"), "Valeria Mendoza", 3256783901L, 8901230987L, null);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
