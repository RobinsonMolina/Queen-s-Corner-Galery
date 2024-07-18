package co.edu.uptcSoft.logic;

import co.edu.uptcSoft.model.Customer;
import co.edu.uptcSoft.model.Materials;
import co.edu.uptcSoft.model.Order;
import co.edu.uptcSoft.model.Supply;
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
    private Order order;
    private Customer customer;

    public Logic() {
        this.orderList = new TreeMap<>();
        customerList = new TreeMap<>();
        customer = new Customer();
        supplyList = new TreeMap<>();
        managementFile = new ManagementFile();
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

   public void addCustomer(String name, long documentNumber, String email, String address, long phoneNumber, Order order) {
       System.out.println("11");
        Customer customer = new Customer(name, documentNumber, email, address, phoneNumber);
        customerList.put(documentNumber, customer);
        if (order != null) {
       System.out.println("22");
            customer.addOrder(order);
            order.setCustomer(customer);
            managementFile.writeCustomersJsonToFile("Customers", customerList);
        }
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
        System.out.println(order.toString());
        orderList.put(orderNumber, order);
        managementFile.writeOrdersJsonToFile("Orders", orderList);
    }

    public TreeMap<Integer, Order> getOrderList() {
        return orderList;
    }

    public TreeMap<Long, Customer> getCustomerList() {
        return customerList;
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
            try {
                if (order.getValue().getStatus().equals("Por Hacer")) {
                    ordersDo.add("Orden No. "+order.getValue().getOrderNumber() + " " + order.getValue().getProductName());
                }
                if (order.getValue().getStatus().equals("En Progreso")) {
                    ordersProgress.add("Orden No. "+order.getValue().getOrderNumber() + " " + order.getValue().getProductName());
                }
                if (order.getValue().getStatus().equals("Entregado")) {
                    ordersDelivered.add("Orden No. "+order.getValue().getOrderNumber() + " " + order.getValue().getProductName());
                }
            }catch (Exception e){

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
        managementFile.writeJsonToFile("Supplies",supplyList);
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
        managementFile.writeJsonToFile("Supplies",supplyList);
    }

    public void deleteSupply(String row) {
        supplyList.remove(row);
        managementFile.writeJsonToFile("Supplies",supplyList);
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

    public void deleteCustomer(long row) {
        customerList.remove(row);
    }
}
