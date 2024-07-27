package co.edu.uptcSoft.logic;

import co.edu.uptcSoft.model.*;

import java.util.*;

public class Logic {

    private static Logic instance; // Single instance of Logic
    private ManagementFile managementFile;
    private TreeMap<Long, Customer> customerList;
    private TreeMap<Integer, Order> orderList;
    private ArrayList<String> ordersDo;
    private ArrayList<String> ordersProgress;
    private ArrayList<String> ordersDelivered;
    private TreeMap<String, Supply> supplyList;
    private Administrator administrator;

    public Logic() {
        this.orderList = new TreeMap<>();
        this.customerList = new TreeMap<>();
        this.supplyList = new TreeMap<>();
        this.managementFile = new ManagementFile();
        this.administrator = new Administrator();
        loadOrders();
        loadCustomers();
        loadSupply();
        ordersCard();
    }

    // Static method to get the single instance of Logic
    public static Logic getInstance() {
        if (instance == null) {
            instance = new Logic();
        }
        return instance;
    }

    public void addOrder(String productName, String status, int orderNumber, String type, Date productionDate,
                         Date deliveryDate, Customer customer, ArrayList<Materials> materials) {
        Order order = new Order(productName, status, orderNumber, type, productionDate, deliveryDate, customer, null);
        if (!customerList.containsKey(customer.getDocumentNumber())) {
            addCustomer(customer.getName(), customer.getDocumentNumber(), customer.getEmail(), customer.getAddress(), customer.getPhoneNumber(), order);
        } else {
            Customer customer2 = customerList.get(customer.getDocumentNumber());
            customer2.addOrder(order);
            order.setCustomer(customer2);
        }
        orderList.put(orderNumber, order);
        ordersCard();
        managementFile.writeOrdersJsonToFile("Orders", orderList);
    }

    public TreeMap<Integer, Order> getOrderList() {
        return orderList;
    }

    public void deleteOrder(long row) {
        orderList.remove((int) row);
        ordersCard();
        managementFile.writeOrdersJsonToFile("Orders", orderList);
    }

    public void ordersCard(){
        ordersDo = new ArrayList<>();
        ordersProgress = new ArrayList<>();
        ordersDelivered = new ArrayList<>();

        for (Map.Entry<Integer, Order> order : orderList.entrySet()) {
            if (order.getValue().getStatus().equals("Por Hacer")) {
                ordersDo.add("Orden No. "+order.getValue().getOrderNumber() + " " + order.getValue().getProductName());
            }
            if (order.getValue().getStatus().equals("En Progreso")) {
                ordersProgress.add("Orden No. "+order.getValue().getOrderNumber() + " " + order.getValue().getProductName());
            }
            if (order.getValue().getStatus().equals("Entregado")) {
                ordersDelivered.add("Orden No. "+order.getValue().getOrderNumber() + " " + order.getValue().getProductName());
            }
        }
    }

    public ArrayList<String> getOrdersDo() {
        return ordersDo;
    }

    public ArrayList<String> getOrdersProgress() {
        return ordersProgress;
    }

    public ArrayList<String> getOrdersDelivered() {
        return ordersDelivered;
    }

    //////////////////////////////SUPPLY///////////////////////////////////////////

    public void addSupply(String material, String category, String characteristics, int quantity, String unit, int unitPrice, int totalPrice) {

        String id = Character.toUpperCase(category.charAt(0)) + String.format("%03d", supplyList.size()+1);
        supplyList.put(id, new Supply(id, material, category, characteristics, quantity, unit, unitPrice, totalPrice));
        managementFile.writeSuppliyJsonToFile("Supplies",supplyList);
    }

    public TreeMap<String, Supply> getSupplyList() {
        return supplyList;
    }

    public Supply searchSupply(String id) {
        return supplyList.get(id);
    }

    public void updateSupply(String id, String material, String category, String characteristics, int quantity, String unit, long unitPrice, long totalPrice) {
        supplyList.remove(id);
        supplyList.put(id, new Supply(id, material, category, characteristics, quantity, unit, unitPrice, totalPrice));
        managementFile.writeSuppliyJsonToFile("Supplies",supplyList);
    }

    public void deleteSupply(String row) {
        supplyList.remove(row);
        managementFile.writeSuppliyJsonToFile("Supplies",supplyList);
    }

    public void loadOrders() {
        orderList = managementFile.readOrdersFromJson();
    }

    public void loadCustomers() {
        customerList = managementFile.readCustomersFromJson();
    }

    public void loadSupply() {
        supplyList = managementFile.readSupplyFromJson();
    }

    //////////////////////////////Customers///////////////////////////////////////////

    // Method to add a customer to the customerList
    public void addCustomer(String name, long documentNumber, String email, String address, long phoneNumber, Order order) {
        Customer customer = new Customer(name, documentNumber, email, address, phoneNumber);

        boolean hasOrders = false;
        for (Map.Entry<Integer, Order> entry : orderList.entrySet()) {
            Order existingOrder = entry.getValue();
            Customer existingCustomer = existingOrder.getCustomer();

            if (existingCustomer != null && existingCustomer.getDocumentNumber() == documentNumber) {
                hasOrders = true;
                customer.addOrder(existingOrder);
                existingOrder.setCustomer(customer);
            }
        }

        if (!hasOrders && order != null) {
            customer.addOrder(order);
            order.setCustomer(customer);
        }

        customerList.put(documentNumber, customer);
        managementFile.writeCustomersJsonToFile("Customers", customerList);
    }

    public TreeMap<Long, Customer> getCustomerList() {
        return customerList;
    }

    public void deleteCustomer(Long row) {
        customerList.remove(row);
        managementFile.writeCustomersJsonToFile("Customers", customerList);
    }

    public String Email(String email) {

        String[] parts = email.split("@");

        if (parts.length != 2) {
            if (email.contains("@")) {
                return "Correo no válido";
            }
            return "El correo debe tener un @";
        }

        if (parts[0].length() == 0) {
            return "El correo debe tener caracteres antes del @";
        }

        if (parts[0].length() < 4) {
            return "El correo debe tener al menos 5 caracteres";
        }

        if (parts[1].length() == 0) {
            return "El correo debe tener un dominio";
        }

        if (!parts[1].contains(".")) {
            return "El correo no tiene un dominio";
        }

        if (parts[1].length() < 4) {
            return "El correo debe tener un dominio de al menos 5 caracteres";
        }

        administrator.setEmail(email);
        administrator.setPassword(getPassword());
        managementFile.writeAdministratorJsonToFile("Administrator", administrator);
        return "Creado";
    }

    public String Password(String password) {

        if (password.length() < 8) {
            return "La contraseña debe tener al menos 8 caracteres";
        }

        if (password.length() > 16) {
            return "La contraseña debe tener menos de 16 caracteres";
        }

        if (!password.matches(".*[0-9].*")) {
            return "La contraseña debe tener al menos un número";
        }

        if (!password.matches(".*[a-z].*")) {
            return "La contraseña debe tener al menos una letra";
        }

        if (!password.matches(".*[A-Z].*")) {
            return "La contraseña debe tener al menos una mayúscula";
        }
        administrator.setEmail(getEmail());
        administrator.setPassword(password);
        managementFile.writeAdministratorJsonToFile("Administrator", administrator);
        return "Creado";
    }

    public String getPassword() {
        return managementFile.readAdministratorFromJson().getPassword();
    }

    public String getEmail() {
        return managementFile.readAdministratorFromJson().getEmail();
    }
}
