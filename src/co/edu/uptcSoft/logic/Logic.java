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
        //loadCustomerss();
        loadOrders();
        loadCustomers();
        //loadCustomerss();
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
                         Date deliveryDate, String name, long phoneNumber, long documentNumber, ArrayList<Materials> materials) {
        Order order = new Order(productName, status, orderNumber, type, productionDate, deliveryDate, null, null);
        if (!customerList.containsKey(documentNumber)) {
            addCustomer(name, documentNumber, "", "", phoneNumber, order);
        } else {
            Customer customer = customerList.get(documentNumber);
            customer.addOrder(order);
            order.setCustomer(customer);
        }
        orderList.put(orderNumber, order);
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
        /*addOrder("SofaCama", "Por Hacer", 1, "Mueble", new Date(), new Date(), "Juan David Pérez", 3001234567l, 1234567890l, new ArrayList<>());
        addOrder("Mesa De Centro", "Entregado", 2, "Mueble", new Date(), new Date(), "María Alejandra Rodríguez", 339876543l, 2345678901l, new ArrayList<>());
        addOrder("Cojines", "Por Hacer", 3, "Mueble", new Date(), new Date(), "Carlos Andrés Gómez", 323456789l, 3456789012l, new ArrayList<>());
        addOrder("Lampara", "En Progreso", 4, "Mueble", new Date(), new Date(), "Luisa Fernanda Fernández", 3109876543l, 4567890123l, new ArrayList<>());
        addOrder("Sofa", "Entregado", 5, "Mueble", new Date(), new Date(), "Andrés Felipe López", 316879801l, 5678901234l, new ArrayList<>());
        addOrder("SofaCama", "En Progreso", 6, "Mueble", new Date(), new Date(), "Ana María Torres", 318678902l, 6789012345l, new ArrayList<>());
        addOrder("SofaCama", "Por Hacer", 7, "Mueble", new Date(), new Date(), "Diego Alejandro Martínez", 317890123l, 7890123456l, new ArrayList<>());
        addOrder("SofaCama", "Entregado", 8, "Mueble", new Date(), new Date(), "Laura Patricia Ramírez", 3198901234l, 8901234567l, new ArrayList<>());
        addOrder("Lampara", "Por Hacer", 9, "Mueble", new Date(), new Date(), "Pedro José Jiménez", 3109021345l, 9012345678l, new ArrayList<>());
        addOrder("Mesa De Centro", "En Progreso", 10, "Mueble", new Date(), new Date(), "Sofía Margarita Morales", 3190173456l, 1234509876l, new ArrayList<>());
        addOrder("Cojines", "Entregado", 11, "Mueble", new Date(), new Date(), "Jorge Luis Ríos", 3111234567l, 2345609876l, new ArrayList<>());
        addOrder("Mesa De Centro", "En Progreso", 12, "Mueble", new Date(), new Date(), "Sofía Alejandra Rodríguez", 3190173457l, 3456709876l, new ArrayList<>());
        addOrder("SofaCama", "Por Hacer", 13, "Mueble", new Date(), new Date(), "Alberto Gómez", 3111234568l, 4567809876l, new ArrayList<>());
        addOrder("Mesa De Centro", "En Progreso", 14, "Mueble", new Date(), new Date(), "Santiago Herrera", 3109021347l, 5678909876l, new ArrayList<>());
        addOrder("Lampara", "Entregado", 15, "Mueble", new Date(), new Date(), "Ricardo Pérez", 3190173458l, 6789010987l, new ArrayList<>());
        addOrder("Sofa", "En Progreso", 16, "Mueble", new Date(), new Date(), "Daniela Torres", 3111234569l, 7890120987l, new ArrayList<>());
        addOrder("SofaCama", "Entregado", 17, "Mueble", new Date(), new Date(), "Valeria Mendoza", 3109021347l, 8901230987l, new ArrayList<>());
        addOrder("Lampara", "Por Hacer", 18, "Mueble", new Date(), new Date(), "Juan Carlos Ruiz", 3190173458l, 9012345681l, new ArrayList<>());
        addOrder("SofaCama", "En Progreso", 19, "Mueble", new Date(), new Date(), "Paula Andrea García", 3111234570l, 1234567891l, new ArrayList<>());
        addOrder("Mesa De Centro", "Por Hacer", 20, "Mueble", new Date(), new Date(), "Miguel Ángel Ríos", 3109021348l, 2345678902l, new ArrayList<>());
        addOrder("Lampara", "Entregado", 21, "Mueble", new Date(), new Date(), "Luis Fernando Martínez", 3190173459l, 3456789013l, new ArrayList<>());
        addOrder("Lampara", "En Progreso", 22, "Mueble", new Date(), new Date(), "Laura Camila Torres", 3111234571l, 4567890124l, new ArrayList<>());
        addOrder("Sofa", "Entregado", 23, "Mueble", new Date(), new Date(), "José Manuel Gómez", 3109021347l, 5678901235l, new ArrayList<>());
        addOrder("SofaCama", "Por Hacer", 24, "Mueble", new Date(), new Date(), "Ana Patricia Ramírez", 3190173460l, 6789012346l, new ArrayList<>());
        addOrder("SofaCama", "En Progreso", 25, "Mueble", new Date(), new Date(), "Carlos Andrés López", 3111234572l, 7890123457l, new ArrayList<>());
        addOrder("SofaCama", "Entregado", 26, "Mueble", new Date(), new Date(), "Mónica Isabel Pérez", 3109021349l, 8901234568l, new ArrayList<>());
        addOrder("SofaCama", "Por Hacer", 27, "Mueble", new Date(), new Date(), "Sergio Andrés Jiménez", 3190173460l, 9012345680l, new ArrayList<>());
        addOrder("Lampara", "En Progreso", 28, "Mueble", new Date(), new Date(), "Lucía Fernanda Morales", 3111234573l, 1234509877l, new ArrayList<>());
        addOrder("Sofa", "En Progreso", 29, "Mueble", new Date(), new Date(), "Sofía Alejandra Rodríguez", 3190173460l, 2345609877l, new ArrayList<>());*/
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

    public void loadCustomerss() {
        addCustomer("Juan David Pérez", 1234567890L, "juan@perez.com", "Calle 123, Ciudad 1", 3001234567L, new Order());
        addCustomer("María Alejandra Rodríguez", 2345678901L, "mar@alejandra.com", "Calle 456, Ciudad 2", 339876543L, new Order());
        addCustomer("Carlos Andrés Gómez", 3456789012L, "carlos@gomez.com", "Calle 789, Ciudad 3", 323456789L, new Order());
        addCustomer("Luisa Fernanda Fernández", 4567890123L, "luisa@fernandez.com", "Calle 101, Ciudad 4", 3109876543L, new Order());
        addCustomer("Andrés Felipe López", 5678901234L, "andres@lopez.com", "Calle 112, Ciudad 5", 316879801L, new Order());
        addCustomer("Ana María Torres", 6789012345L, "ana@torres.com", "Calle 131, Ciudad 6", 318678902L, new Order());
        addCustomer("Diego Alejandro Martínez", 7890123456L, "diego@martinez.com", "Calle 123, Piso 1, Edificio 1", 317890123L, new Order());
        addCustomer("Laura Patricia Ramírez", 8901234567L, "laura@ramirez.com", "Calle 123, Piso 1, Edificio 1", 3198901234L, new Order());
        addCustomer("Pedro José Jiménez", 9012345678L, "pedro@jimenez.com", "Calle 123, Piso 1, Edificio 1", 3109021345L, new Order());
        addCustomer("Sofía Margarita Morales", 1234509876L, "sofia@morales.com", "Calle 123, Piso 1, Edificio 1", 3190173456L, new Order());
        addCustomer("Jorge Luis Ríos", 2345609876L, "jorge@rios.com", "Calle 123, Piso 1, Edificio 1", 3111234567L, new Order());
        addCustomer("Alberto Gómez", 4567809876L, "alberto@gomez.com", "Calle 123, Piso 1, Edificio 1", 3111234568L, new Order());
        addCustomer("Santiago Herrera", 5678909876L, "santiago@herrera.com", "Calle 123, Piso 1, Edificio 1", 3109021347L, new Order());
        addCustomer("Ricardo Pérez", 6789010987L, "ricardo@perez.com", "Calle 123, Piso 1, Edificio 1", 3190173458L, new Order());
        addCustomer("Daniela Torres", 7890120987L, "daniela@torres.com", "Calle 123, Piso 1, Edificio 1", 3111234569L, new Order());
        addCustomer("Valeria Mendoza", 8901230987L, "valeria@mendoza.com", "Calle 123, Piso 1, Edificio 1", 3109021347L, new Order());
        addCustomer("Juan Carlos Ruiz", 9012345681L, "juan@carlos.com", "Calle 123, Piso 1, Edificio 1", 3190173458L, new Order());
        addCustomer("Paula Andrea García", 1234567891L, "paula@andrea.com", "Calle 123, Piso 1, Edificio 1", 3111234570L, new Order());
        addCustomer("Miguel Ángel Ríos", 2345678902L, "miguel@rios.com", "Calle 123, Piso 1, Edificio 1", 3109021348L, new Order());
        addCustomer("Luis Fernando Martínez", 3456789013L, "luis@martinez.com", "Calle 123, Piso 1, Edificio 1", 3190173459L, new Order());
        addCustomer("Laura Camila Torres", 4567890124L, "laura@torres.com", "Calle 123, Piso 1, Edificio 1", 3111234571L, new Order());
        addCustomer("José Manuel Gómez", 5678901235L, "jose@gomez.com", "Calle 123, Piso 1, Edificio 1", 3109021347L, new Order());
        addCustomer("Ana Patricia Ramírez", 6789012346L, "ana@ramirez.com", "Calle 123, Piso 1, Edificio 1", 3190173460L, new Order());
        addCustomer("Carlos Andrés López", 7890123457L, "carlos@lopez.com", "Calle 123, Piso 1, Edificio 1", 3111234572L, new Order());
        addCustomer("Mónica Isabel Pérez", 8901234568L, "monica@perez.com", "Calle 123, Piso 1, Edificio 1", 3109021349L, new Order());
        addCustomer("Sergio Andrés Jiménez", 9012345680L, "sergio@jimenez.com", "Calle 123, Piso 1, Edificio 1", 3190173460L, new Order());
        addCustomer("Lucía Fernanda Morales", 1234509877L, "lucia@morales.com", "Calle 123, Piso 1, Edificio 1", 3111234573L, new Order());
        addCustomer("Sofía Alejandra Rodríguez", 2345609877L, "sofia@rodriguez.com", "Calle 123, Piso 1, Edificio 1", 3190173457L, new Order());
        addCustomer("Roberto Carlos Gómez", 3456709877L, "roberto@gomez.com", "Calle 123, Piso 1, Edificio 1", 3111234574L, new Order());
        addCustomer("Valentina Herrera", 4567809877L, "valentina@herrera.com", "Calle 123, Piso 1, Edificio 1", 3190173461L, new Order());
        addCustomer("Ramón Pérez", 5678909877L, "ramon@perez.com", "Calle 123, Piso 1, Edificio 1", 3111234575L, new Order());
        addCustomer("Sebastián Torres", 6789010988L, "sebastian@torres.com", "Calle 123, Piso 1, Edificio 1", 3190173462L, new Order());
        addCustomer("Lina Mendoza", 7890120988L, "lina@mendoza.com", "Calle 123, Piso 1, Edificio 1", 3111234576L, new Order());
        addCustomer("Natalia Ruiz", 8901230988L, "natalia@ruiz.com", "Calle 123, Piso 1, Edificio 1", 3109021350L, new Order());
        addCustomer("Javier García", 9012345681L, "javier@garcia.com", "Calle 123, Piso 1, Edificio 1", 3190173463L, new Order());
        addCustomer("María Paula Jiménez", 9123456789L, "maria@jimenez.com", "Calle 123, Piso 1, Edificio 1", 3109021351L, new Order());
        addCustomer("Andrés Felipe Gómez", 9234567890L, "andres@gomez.com", "Calle 123, Piso 1, Edificio 1", 3190173464L, new Order());
        addCustomer("Camila Andrea Ramírez", 9345678901L, "camila@ramirez.com", "Calle 123, Piso 1, Edificio 1", 3111234577L, new Order());
        addCustomer("Diego Alejandro Torres", 9456789012L, "diego@torres.com", "Calle 123, Piso 1, Edificio 1", 3109021352L, new Order());
        addCustomer("Laura Valentina López", 9567890123L, "laura@lopez.com", "Calle 123, Piso 1, Edificio 1", 3190173465L, new Order());
        addCustomer("Pedro José Martínez", 9678901234L, "pedro@martinez.com", "Calle 123, Piso 1, Edificio 1", 3111234578L, new Order());
        addCustomer("Sofía Alejandra Pérez", 9789012345L, "sofia@perez.com", "Calle 123, Piso 1, Edificio 1", 3109021353L, new Order());
        addCustomer("Jorge Luis Gómez", 9890123456L, "jorge@gomez.com", "Calle 123, Piso 1, Edificio 1", 3190173466L, new Order());
        addCustomer("Ana María Herrera", 9901234567L, "ana@herrera.com", "Calle 123, Piso 1, Edificio 1", 3111234579L, new Order());
        addCustomer("Lucas Alejandro Torres", 9012345678L, "lucas@torres.com", "Calle 123, Piso 1, Edificio 1", 3109021354L, new Order());
        addCustomer("Valentina Martínez", 9123456789L, "valentina@martinez.com", "Calle 123, Piso 1, Edificio 1", 3190173467L, new Order());
        addCustomer("Miguel Ángel Ramírez", 9234567890L, "miguel@ramirez.com", "Calle 123, Piso 1, Edificio 1", 3111234580L, new Order());
        addCustomer("María José López", 9345678901L, "maria@lopez.com", "Calle 123, Piso 1, Edificio 1", 3109021355L, new Order());
        addCustomer("Andrea Gómez", 9456789012L, "andrea@gomez.com", "Calle 123, Piso 1, Edificio 1", 3190173468L, new Order());
        addCustomer("Diego Fernando Pérez", 9567890123L, "diego@perez.com", "Calle 123, Piso 1, Edificio 1", 3111234581L, new Order());
        addCustomer("Laura Sofía Gómez", 9678901234L, "laura@gomez.com", "Calle 123, Piso 1, Edificio 1", 3109021356L, new Order());
        addCustomer("Camilo Andrés Ramírez", 9789012345L, "camilo@ramirez.com", "Calle 123, Piso 1, Edificio 1", 3190173469L, new Order());
        addCustomer("Valeria Alejandra López", 9890123456L, "valeria@lopez.com", "Calle 123, Piso 1, Edificio 1", 3111234582L, new Order());
        addCustomer("Juan José Gómez", 9901234567L, "juan@gomez.com", "Calle 123, Piso 1, Edificio 1", 3109021357L, new Order());
    }

    public void deleteCustomer(String row) {
        customerList.remove(Long.parseLong(row));
        managementFile.writeCustomersJsonToFile("Customers", customerList);
    }
}
