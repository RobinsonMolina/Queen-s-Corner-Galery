package co.edu.uptcSoft.model;

import com.google.gson.annotations.Expose;

public class Supply {

    @Expose private String id;
    @Expose private String material;
    @Expose private String category;
    @Expose private String characteristics;
    @Expose private int quantity;
    @Expose private String unit;
    @Expose private long unitPrice;
    @Expose private long totalPrice;

    public Supply() {
    }

    public Supply(String id, String material, String category, String characteristics, int quantity, String unit, long unitPrice, long totalPrice) {
        this.id = id;
        this.material = material;
        this.category = category;
        this.characteristics = characteristics;
        this.quantity = quantity;
        this.unit = unit;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Supply{" +
                "id='" + id + '\'' +
                ", material='" + material + '\'' +
                ", category='" + category + '\'' +
                ", characteristics='" + characteristics + '\'' +
                ", quantity=" + quantity +
                ", unit='" + unit + '\'' +
                ", unitPrice=" + unitPrice +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
