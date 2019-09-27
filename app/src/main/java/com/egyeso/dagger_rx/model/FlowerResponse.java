package com.egyeso.dagger_rx.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FlowerResponse {

    @Expose
    @SerializedName("productId")
    private int productid;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("photo")
    private String photo;
    @Expose
    @SerializedName("instructions")
    private String instructions;
    @Expose
    @SerializedName("price")
    private double price;
    @Expose
    @SerializedName("category")
    private String category;

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
