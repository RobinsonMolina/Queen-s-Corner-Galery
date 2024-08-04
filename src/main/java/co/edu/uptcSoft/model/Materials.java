package co.edu.uptcSoft.model;

public class Materials {

    private String code;
    private String material;
    private int quantity;
    private int cost;

    public Materials(String code, String material, int quantity, int cost) {
        this.code = code;
        this.material = material;
        this.quantity = quantity;
        this.cost = cost;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

}
