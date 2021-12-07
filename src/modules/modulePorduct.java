/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modules;

import entities.Category;
import java.util.List;
import java.util.Optional;
import services.categoryService;

/**
 *
 * @author umbke
 */
public class modulePorduct {
    
    private categoryService categoryService; 

    public modulePorduct() { 
        categoryService = new categoryService(); 
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
    
    public List<Category> findAll_States_Categories(Boolean state){
        return categoryService.findAll_States(state);
    }
    
    public Optional<Category> findByName_Categories(String name){
        return categoryService.findByName(name);
    }
    
    public Optional<Category> findById_Categories(int id){
        return categoryService.findById(id);
    }
    
}
