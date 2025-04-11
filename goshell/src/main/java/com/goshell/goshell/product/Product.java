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
    private String sellerId;
    private String image;
    private String type;
    @NotEmpty
    private Float price;
    @NotEmpty
    private Long createdAt;

    public Product(){}

    public Product(String id, String name, String description, String place, String sellerId, String image, String type, Float price, Long createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.place = place;
        this.sellerId = sellerId;
        this.image = image;
        this.type = type;
        this.price = price;
        this.createdAt = createdAt;
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

    public String getSellerId() {
        return sellerId;
    }

    public String getImage() {
        return image;
    }

    public String getType() {
        return type;
    }

    public Float getPrice(){
        return price;
    }

    public Long getCreatedAt(){
        return createdAt;
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

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(Float price){
        this.price = price;
    }

    public void setCreatedAt(Long createdAt){
        this.createdAt = createdAt;
    }
}