/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modules;

import entities.Category;
import entities.Product; 
import java.util.List;
import java.util.Optional; 
import services.categoryService;
import services.productService;
import util.Commons;

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
    
    public String saveCategory(int id, String name, Boolean state){  
        return categoryService.save(id, name);
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
    
    public String registerProduct(String name, String brand, Double price, 
            Integer Stock, String category){
        return productService.save(name, brand, price, Stock, category);
    }
    
    public String updateProduct(int id, String name, String brand, Double price, 
            Integer Stock, String category, Boolean state){
        return productService.update(id, name, brand, price, Stock, category, state);
    }
    
    public String changeStateProduct(int id){
        return productService.changeState(id);
    }
}
