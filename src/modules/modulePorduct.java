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
    
    public String update(int id, String name, int state){
        return categoryService.update(id, name, state);
    }
    
    public List<Category> findAll(){
        return categoryService.findAll();
    }
    
    public List<Category> findAll_States(Boolean state){
        return categoryService.findAll_States(state);
    }
    
    public Optional<Category> findByName(String name){
        return categoryService.findByName(name);
    }
    
    public Optional<Category> findById(int id){
        return categoryService.findById(id);
    }
    
}
