package co.edu.uptcSoft.logic;

import co.edu.uptcSoft.model.Customer;
import co.edu.uptcSoft.model.Materials;
import co.edu.uptcSoft.model.Order;
import co.edu.uptcSoft.model.Supply;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Logic {

    private static Logic instance; // Single instance of Logic
    private TreeMap<Long, Customer> customerList;
    private TreeMap<Integer, Order> orderList;
    private ArrayList<String> ordersDo;
    private ArrayList<String> ordersProgress;
    private ArrayList<String> ordersDelivered;
    private TreeMap<String, Supply> supplyList;
    private Order order;
    private Customer customer;

    private Logic() {
        customerList = new TreeMap<>();
        orderList = new TreeMap<>();
        customer = new Customer();
        supplyList = new TreeMap<>();
        loadCustomers();
        loadOrders();
        loadSupplies();
        ordersCard();
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
            addOrder("Mesa De Centro", "Entregado", 2, "Mueble", sdf.parse("12/06/2024"), sdf.parse("27/06/2024"), "María Alejandra Rodríguez", 339876543L, 2345678901L, null);
            addOrder("Cojines", "Por Hacer", 3, "Mueble", sdf.parse("15/06/2024"), sdf.parse("30/06/2024"), "Carlos Andrés Gómez", 323456789L, 3456789012L, null);
            addOrder("Lampara", "En Progreso", 4, "Mueble", sdf.parse("17/06/2024"), sdf.parse("02/07/2024"), "Luisa Fernanda Fernández", 3109876543L, 4567890123L, null);
            addOrder("Sofa", "Entregado", 5, "Mueble", sdf.parse("20/06/2024"), sdf.parse("05/07/2024"), "Andrés Felipe López", 316879801L, 5678901234L, null);
            addOrder("SofaCama", "En Progreso", 6, "Mueble", sdf.parse("22/06/2024"), sdf.parse("07/07/2024"), "Ana María Torres", 318678902L, 6789012345L, null);
            addOrder("SofaCama", "Por Hacer", 7, "Mueble", sdf.parse("25/06/2024"), sdf.parse("10/07/2024"), "Diego Alejandro Martínez", 317890123L, 7890123456L, null);
            addOrder("SofaCama", "Entregado", 8, "Mueble", sdf.parse("27/06/2024"), sdf.parse("12/07/2024"), "Laura Patricia Ramírez", 3198901234L, 8901234567L, null);
            addOrder("Lampara", "Por Hacer", 9, "Mueble", sdf.parse("29/06/2024"), sdf.parse("14/07/2024"), "Pedro José Jiménez", 3109021345L, 9012345678L, null);
            addOrder("Mesa De Centro", "En Progreso", 10, "Mueble", sdf.parse("01/06/2024"), sdf.parse("16/06/2024"), "Sofía Margarita Morales", 3190173456L, 1234509876L, null);
            addOrder("Cojines", "Entregado", 11, "Mueble", sdf.parse("03/06/2024"), sdf.parse("18/06/2024"), "Jorge Luis Ríos", 3111234567L, 2345609876L, null);
            addOrder("Mesa De Centro", "En Progreso", 12, "Mueble", sdf.parse("05/06/2024"), sdf.parse("20/06/2024"), "María Alejandra Rodríguez", 3398763543L, 3456709876L, null);
            addOrder("SofaCama", "Por Hacer", 13, "Mueble", sdf.parse("07/06/2024"), sdf.parse("22/06/2024"), "Alberto Gómez", 3209876534L, 4567809876L, null);
            addOrder("Mesa De Centro", "En Progreso", 14, "Mueble", sdf.parse("10/06/2024"), sdf.parse("24/06/2024"), "Santiago Herrera", 3123453678L, 5678909876L, null);
            addOrder("Lampara", "Entregado", 15, "Mueble", sdf.parse("11/06/2024"), sdf.parse("26/06/2024"), "Ricardo Pérez", 3219876534L, 6789010987L, null);
            addOrder("Mesa De Centro", "En Progreso", 16, "Mueble", sdf.parse("13/06/2024"), sdf.parse("28/06/2024"), "Daniela Torres", 3245678390L, 7890120987L, null);
            addOrder("Mesa De Centro", "Por Hacer", 17, "Mueble", sdf.parse("15/06/2024"), sdf.parse("30/06/2024"), "Valeria Mendoza", 3256783901L, 8901230987L, null);
            addOrder("SofaCama", "Entregado", 18, "Mueble", sdf.parse("10/06/2024"), sdf.parse("25/06/2024"), "Juan Carlos Ruiz", 3001234568L, 1234567891L, null);
            addOrder("Mesa De Centro", "En Progreso", 19, "Mueble", sdf.parse("12/06/2024"), sdf.parse("27/06/2024"), "Paula Andrea García", 339876544L, 2345678902L, null);
            addOrder("Cojines", "Por Hacer", 20, "Mueble", sdf.parse("15/06/2024"), sdf.parse("30/06/2024"), "Miguel Ángel Ríos", 323456790L, 3456789013L, null);
            addOrder("Lampara", "En Progreso", 21, "Mueble", sdf.parse("17/06/2024"), sdf.parse("02/07/2024"), "Luis Fernando Martínez", 3109876544L, 4567890124L, null);
            addOrder("Sofa", "Entregado", 22, "Mueble", sdf.parse("20/06/2024"), sdf.parse("05/07/2024"), "Laura Camila Torres", 316879802L, 5678901235L, null);
            addOrder("SofaCama", "En Progreso", 23, "Mueble", sdf.parse("22/06/2024"), sdf.parse("07/07/2024"), "José Manuel Gómez", 318678903L, 6789012346L, null);
            addOrder("SofaCama", "Por Hacer", 24, "Mueble", sdf.parse("25/06/2024"), sdf.parse("10/07/2024"), "Ana Patricia Ramírez", 317890124L, 7890123457L, null);
            addOrder("SofaCama", "Entregado", 25, "Mueble", sdf.parse("27/06/2024"), sdf.parse("12/07/2024"), "Carlos Andrés López", 3198901235L, 8901234568L, null);
            addOrder("Lampara", "Por Hacer", 26, "Mueble", sdf.parse("29/06/2024"), sdf.parse("14/07/2024"), "Mónica Isabel Pérez", 3109021346L, 9012345679L, null);
            addOrder("Mesa De Centro", "En Progreso", 27, "Mueble", sdf.parse("01/06/2024"), sdf.parse("16/06/2024"), "Sergio Andrés Jiménez", 3190173457L, 1234509877L, null);
            addOrder("Cojines", "Entregado", 28, "Mueble", sdf.parse("03/06/2024"), sdf.parse("18/06/2024"), "Lucía Fernanda Morales", 3111234568L, 2345609877L, null);
            addOrder("Mesa De Centro", "En Progreso", 29, "Mueble", sdf.parse("05/06/2024"), sdf.parse("20/06/2024"), "Sofía Alejandra Rodríguez", 3398763544L, 3456709877L, null);
            addOrder("SofaCama", "Por Hacer", 30, "Mueble", sdf.parse("07/06/2024"), sdf.parse("22/06/2024"), "Roberto Carlos Gómez", 3209876535L, 4567809877L, null);
            addOrder("Mesa De Centro", "En Progreso", 31, "Mueble", sdf.parse("10/06/2024"), sdf.parse("24/06/2024"), "Valentina Herrera", 3123453679L, 5678909877L, null);
            addOrder("Sofa", "Entregado", 32, "Mueble", sdf.parse("11/06/2024"), sdf.parse("26/06/2024"), "Ramón Pérez", 3219876535L, 6789010988L, null);
            addOrder("Mesa De Centro", "En Progreso", 33, "Mueble", sdf.parse("13/06/2024"), sdf.parse("28/06/2024"), "Sebastián Torres", 3245678391L, 7890120988L, null);
            addOrder("Sofa", "Por Hacer", 34, "Mueble", sdf.parse("15/06/2024"), sdf.parse("30/06/2024"), "Lina Mendoza", 3256783902L, 8901230988L, null);
            addOrder("SofaCama", "Entregado", 35, "Mueble", sdf.parse("10/06/2024"), sdf.parse("25/06/2024"), "Natalia Ruiz", 3001234569L, 1234567892L, null);
            addOrder("Mesa De Centro", "En Progreso", 36, "Mueble", sdf.parse("12/06/2024"), sdf.parse("27/06/2024"), "Javier García", 339876545L, 2345678903L, null);
            addOrder("Cojines", "Por Hacer", 37, "Mueble", sdf.parse("15/06/2024"), sdf.parse("30/06/2024"), "Gabriela Ríos", 323456791L, 3456789014L, null);
            addOrder("Lampara", "En Progreso", 38, "Mueble", sdf.parse("17/06/2024"), sdf.parse("02/07/2024"), "Mauricio Martínez", 3109876545L, 4567890125L, null);
            addOrder("Sofa", "Entregado", 39, "Mueble", sdf.parse("20/06/2024"), sdf.parse("05/07/2024"), "Lorena Torres", 316879803L, 5678901236L, null);
            addOrder("SofaCama", "En Progreso", 40, "Mueble", sdf.parse("22/06/2024"), sdf.parse("07/07/2024"), "Esteban Gómez", 318678904L, 6789012347L, null);
            addOrder("SofaCama", "Por Hacer", 41, "Mueble", sdf.parse("25/06/2024"), sdf.parse("10/07/2024"), "Mariana Ramírez", 317890125L, 7890123458L, null);
            addOrder("SofaCama", "Entregado", 42, "Mueble", sdf.parse("27/06/2024"), sdf.parse("12/07/2024"), "Felipe López", 3198901236L, 8901234569L, null);
            addOrder("Lampara", "Por Hacer", 43, "Mueble", sdf.parse("29/06/2024"), sdf.parse("14/07/2024"), "Santiago Pérez", 3109021347L, 9012345680L, null);
            addOrder("Mesa De Centro", "En Progreso", 44, "Mueble", sdf.parse("01/06/2024"), sdf.parse("16/06/2024"), "Valeria Jiménez", 3190173458L, 1234509878L, null);
            addOrder("Cojines", "Entregado", 45, "Mueble", sdf.parse("03/06/2024"), sdf.parse("18/06/2024"), "Carolina Morales", 3111234569L, 2345609878L, null);
            addOrder("Mesa De Centro", "En Progreso", 46, "Mueble", sdf.parse("05/06/2024"), sdf.parse("20/06/2024"), "Diego Rodríguez", 3398763545L, 3456709878L, null);
            addOrder("Sofa", "Por Hacer", 47, "Mueble", sdf.parse("07/06/2024"), sdf.parse("22/06/2024"), "Miguel Gómez", 3209876536L, 4567809878L, null);
            addOrder("Sofa", "En Progreso", 48, "Mueble", sdf.parse("10/06/2024"), sdf.parse("24/06/2024"), "Catalina Herrera", 3123453680L, 5678909878L, null);
            addOrder("Mesa De Centro", "Entregado", 49, "Mueble", sdf.parse("11/06/2024"), sdf.parse("26/06/2024"), "Samuel Pérez", 3219876536L, 6789010989L, null);
            addOrder("SofaCama", "En Progreso", 50, "Mueble", sdf.parse("13/06/2024"), sdf.parse("28/06/2024"), "Andrea Torres", 3245678392L, 7890120989L, null);
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
        ordersCard();
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


    private void loadSupplies() {
        addSupply("Lino", "Telas", "Resistente, Transpirable, Beige", 50, "Metros", 80000, 40000);
        addSupply("Chenille", "Telas", "Textura Suave, Verde", 40, "Metros", 95000, 40000);
        addSupply("Terciopelo", "Telas", "Lujo, Suave, Rojo", 40, "Metros", 120000, 40000);
        addSupply("Cuero Sintético", "Telas", "Fácil De Limpiar, Resistente, Negro", 55, "Metros", 90000, 55000);
        addSupply("Pana", "Telas", "Textura Acanalada,Duradero,Maron", 30, "Metros", 100000, 30000);
        addSupply("Jacquard", "Telas", "Diseño Intrincado, Resistente, Azul", 35, "Metros", 110000, 35000);
        addSupply("Tweed", "Telas", "Textura Rugosa, Duradero, Gris", 50, "Metros", 75000, 30000);
        addSupply("Franela", "Telas", "Suave, Cálida, Color Gris Claro", 35, "Metros", 45000, 15000);
        addSupply("Seda", "Telas", "Brillante, Suave, Color Blanco Perla", 25, "Metros", 70000, 15000);
        addSupply("Gamuza", "Telas", "Suave Al Tacto, Elegante, Beige", 20, "Metros", 65000, 20000);
        addSupply("Madera", "Muebles", "Rectangular, Color Nogal", 4, "Metros", 60000, 240000);
        addSupply("Sarga", "Telas", "Duradera, Resistente, Verde Militar", 95, "Metros", 95000, 9025000);
        addSupply("Lycra", "Telas", "Elástica, Suave, Color Negro", 50, "Metros", 50000, 2500000);
        addSupply("Raso", "Telas", "Brillante, Suave, Rojo", 70, "Metros", 70000, 4900000);
        addSupply("Batista", "Telas", "Ligera, Transpirable, Color Blanco", 55, "Metros", 55000, 3025000);
        addSupply("Gasa", "Telas", "Ligera, Transparente, Color Rosa", 40, "Metros", 40000, 1600000);
        addSupply("Encaje", "Telas", "Elegante, Decorativa, Color Blanco", 80, "Metros", 80000, 6400000);
        addSupply("Fieltro", "Telas", "Densa, Resistente, Color Marrón", 45, "Metros", 45000, 2025000);
        addSupply("Tafetán", "Telas", "Ligeramente Brillante, Suave, Color Violeta", 70, "Metros", 70000, 4900000);
        addSupply("Madera", "Muebles", "Rectangular, Color Nogal", 4, "Metros", 60000, 240000);
        addSupply("Metal", "Muebles", "Acero Inoxidable, Color Gris", 5, "Metros", 70000, 350000);
        addSupply("Vidrio", "Muebles", "Templado, Transparente", 3, "Metros", 80000, 240000);
        addSupply("Plástico", "Muebles", "Polipropileno, Color Negro", 6, "Metros", 50000, 300000);
        addSupply("Ratán", "Muebles", "Natural, Color Marrón", 7, "Metros", 90000, 630000);
        addSupply("Bambú", "Muebles", "Sostenible, Color Natural", 8, "Metros", 40000, 320000);
        addSupply("Cuero", "Muebles", "Auténtico, Color Negro", 2, "Metros", 100000, 200000);
        addSupply("Pino", "Muebles", "Blando, Color Claro", 5, "Metros", 45000, 225000);
        addSupply("Mármol", "Muebles", "Blanco, Pulido", 1, "Metros", 150000, 150000);
        addSupply("Granit", "Muebles", "Duradero, Color Oscuro", 3, "Metros", 120000, 360000);
        addSupply("Acacia", "Muebles", "Madera Dura, Color Marrón", 4, "Metros", 65000, 260000);
        addSupply("Teca", "Muebles", "Resistente, Color Natural", 6, "Metros", 85000, 510000);
        addSupply("Roble", "Muebles", "Madera Dura, Color Claro", 5, "Metros", 75000, 375000);
    }

    public void addSupply(String material, String category, String characteristics, int quantity, String unit, int unitPrice, int totalPrice) {

        String id = Character.toUpperCase(category.charAt(0)) + String.format("%03d", supplyList.size()+1);
        supplyList.put(id, new Supply(id, material, category, characteristics, quantity, unit, unitPrice, totalPrice));
    }

    public TreeMap<String, Supply> getSupplyList() {
        return supplyList;
    }

    public void deleteSupply(String row) {
        supplyList.remove(row);
    }
}
