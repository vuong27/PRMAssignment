package com.trainh.assignmentprm.entities;

public class Product {
    private int id;
    private int image;
    private String name;
    private double price;
    private String type;
    private String description;

    public Product(int image, String name, double price, String type, String description) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.type = type;
        this.description = description;
    }

    public Product(int id, int image, String name, double price, String type, String description) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.price = price;
        this.type = type;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
