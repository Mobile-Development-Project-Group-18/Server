package com.goshell.goshell.product;

import jakarta.validation.constraints.NotEmpty;

public class Product {
    @NotEmpty
    private String id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotEmpty
    private String place;
    @NotEmpty
    private String seller;
    private String image;
    private String type;

    public Product(){}

    public Product(String id, String name, String description, String place, String seller, String image, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.place = place;
        this.seller = seller;
        this.image = image;
        this.type = type;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPlace() {
        return place;
    }

    public String getSeller() {
        return seller;
    }

    public String getImage() {
        return image;
    }

    public String getType() {
        return type;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setType(String type) {
        this.type = type;
    }
}
