/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Blob;

/**
 *
 * @author kpalmall
 */
public class Product {

    private int id;
    private String name;
    private String brand;
    private Double price;
    private int stock;
    private Blob image;
    private Category category;
    private Boolean state;

    public Product() { 
    }
    
    public Product(String name, String brand, Double price, int stock, Blob image, Category category) { 
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.stock = stock;
        this.image = image;
        this.category = category;
        this.state = true;
    }

    public Product(int id, String name, String brand, Double price, int stock, Blob image, Category category, Boolean state) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.stock = stock;
        this.image = image;
        this.category = category;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
    
}
