package co.edu.uptcSoft.logic;

import co.edu.uptcSoft.model.Administrator;
import co.edu.uptcSoft.model.Customer;
import co.edu.uptcSoft.model.Order;
import co.edu.uptcSoft.model.Supply;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.TreeMap;

public class ManagementFile {

    private File file;
    public static final String filePath = "src/co/edu/uptcSoft/persistence/";
    public static final String fileExtension = ".json";
    private Gson gson;

    public ManagementFile() {
        gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();// Exclude fields without expose annotation
    }

    public void writeOrdersJsonToFile(String fileName, TreeMap<Integer, Order> orderList) {
        Gson gsonForOrders = new GsonBuilder()
                // Register type adapter to remove orders from customers
                .registerTypeAdapter(Customer.class, new JsonSerializer<Customer>() {
                    @Override
                    public JsonElement serialize(Customer src, Type typeOfSrc, JsonSerializationContext context) {
                        JsonObject jsonObject = (JsonObject) gson.toJsonTree(src);
                        jsonObject.remove("orders");
                        return jsonObject;
                    }
                })
                .setPrettyPrinting()
                .create();

        try (FileWriter writer = new FileWriter(filePath + fileName + fileExtension)) {
            gsonForOrders.toJson(orderList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeCustomersJsonToFile(String fileName, TreeMap<Long, Customer> customerList) {
        System.out.println("Llefa");
        Gson gsonForCustomers = new GsonBuilder()
                // Register type adapter to remove customers from customers
                .registerTypeAdapter(Customer.class, new JsonSerializer<Customer>() {
                    @Override
                    public JsonElement serialize(Customer src, Type typeOfSrc, JsonSerializationContext context) {
                        JsonObject jsonObject = (JsonObject) gson.toJsonTree(src);
                        jsonObject.remove("Customer");
                        return jsonObject;
                    }
                })
                .setPrettyPrinting()
                .create();

        try (FileWriter writer = new FileWriter(filePath + fileName + fileExtension)) {
            gsonForCustomers.toJson(customerList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeSuppliyJsonToFile(String fileName, TreeMap<String, Supply> supplyList) {
        try (FileWriter writer = new FileWriter(filePath + fileName + fileExtension)) {
            gson.toJson(supplyList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeAdministratorJsonToFile(String fileName, Administrator administrator) {
        try (FileWriter writer = new FileWriter(filePath + fileName + fileExtension)) {
            gson.toJson(administrator, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TreeMap<Integer, Order> readOrdersFromJson() {
        try (FileReader reader = new FileReader("C:\\Users\\Dani\\Documents\\Uni\\ProgrammingII\\ProgrammingII2024\\Project\\Project JavaFx\\Queens-Corner-Gallery\\src\\main\\java\\co\\edu\\uptcSoft\\persistence\\Orders.json")) {
            Type orderListType = new TypeToken<TreeMap<Integer, Order>>() {}.getType();
            return gson.fromJson(reader, orderListType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public TreeMap<Long,Customer> readCustomersFromJson() {
        try (FileReader reader = new FileReader("C:\\Users\\Dani\\Documents\\Uni\\ProgrammingII\\ProgrammingII2024\\Project\\Project JavaFx\\Queens-Corner-Gallery\\src\\main\\java\\co\\edu\\uptcSoft\\persistence\\Customers.json")) {
            Type customerListType = new TypeToken<TreeMap<Long, Customer>>() {}.getType();
            return gson.fromJson(reader, customerListType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public TreeMap<String, Supply> readSupplyFromJson() {
        try (FileReader reader = new FileReader("C:\\Users\\Dani\\Documents\\Uni\\ProgrammingII\\ProgrammingII2024\\Project\\Project JavaFx\\Queens-Corner-Gallery\\src\\main\\java\\co\\edu\\uptcSoft\\persistence\\Supplies.json")) {
            Type supplyListType = new TypeToken<TreeMap<String, Supply>>() {}.getType();
            return gson.fromJson(reader, supplyListType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Administrator readAdministratorFromJson() {
        try (FileReader reader = new FileReader("C:\\Users\\Dani\\Documents\\Uni\\ProgrammingII\\ProgrammingII2024\\Project\\Project JavaFx\\Queens-Corner-Gallery\\src\\main\\java\\co\\edu\\uptcSoft\\persistence\\Administrator.json")) {
            Type administratorType = new TypeToken<Administrator>() {}.getType();
            return gson.fromJson(reader, administratorType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
