/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modules;

import entities.Category;
import entities.Product;
import java.sql.Blob;
import java.util.List;
import java.util.Optional; 
import services.categoryService;
import services.productService;

/**
 *
 * @author umbke
 */
public class modulePorduct {
    
    private categoryService categoryService; 
    private productService productService; 

    public modulePorduct() { 
        categoryService = new categoryService(); 
        productService = new productService(); 
    }
    
    public String registerCategory(String name){
        return categoryService.save(name);
    }
    
    public String updateCategory(int id, String name, Boolean state){
        return categoryService.update(id, name, state);
    }
    
    public String changeStateCategory(int id){
        return categoryService.changeState(id);
    }
    
    public List<Category> findAll_Categories(){
        return categoryService.findAll();
    }
    
    public List<Category> findByStates_Categories(Boolean state){
        return categoryService.findAll_States(state);
    }
    
    public Optional<Category> findByName_Categories(String name){
        return categoryService.findByName(name);
    }
    
    public Optional<Category> findById_Categories(int id){
        return categoryService.findById(id);
    }
    
    public List<Product> findAll_Products(){
        return productService.findAll();
    }
    
    public String registerProducts(String name, String brand, Double price, Integer Stock, Blob image, String category){
        return productService.save(name, brand, price, Stock, image, category);
    }
}