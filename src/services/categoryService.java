/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Category;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import repository.categoryRepository;
import util.Commons;

/**
 *
 * @author umbke
 */
public class categoryService {
    private categoryRepository categoryRepository;
    
    public categoryService(){
        categoryRepository = new categoryRepository();
    } 
    
    public String save(int id, String name){
        String message = enums.ErrorMessage.REPETED_VALUES.getValue();
        Boolean verify = this.verifyByName(name);
        
        if (!verify) return message;
        
        Category category = Category.builder()
                .name(name)
                .build();
        
        switch(id){
            case 0: 
                message = this.categoryRepository.insert(category);
                break;
            default: 
                category.setId(id);
                message = this.categoryRepository.update(category);
                break;
        }
        return message;
    }
    
    public String changeState(int id){
        String message = enums.ErrorMessage.NOTFOUND.getValue();
        
        if (this.findById(id).isPresent()) {
            Boolean state = !this.findById(id).get().getState();
            message = categoryRepository.changeState(id, state); 
        } 
        return message;
    } 
    
    public Boolean verifyByName(String name){ 
        double count = (double) categoryRepository.findAll().stream()
                .filter(cat -> cat.getName().equals(name))
                .count();
        
        return Commons.DoublesIsEmpty(count);
    }
    
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
    
    public Optional<Category> findByName(String name){
        return categoryRepository.findAll().stream()
                .filter(cat -> cat.getName().equals(name))
                .findFirst();
    }
    
    public Optional<Category> findById(int id){
        return categoryRepository.findAll().stream()
                .filter(rol -> rol.getId()==id )
                .findFirst();
    }
    
    public List<Category> findAll_States(Boolean state){
        return this.findAll().stream()
                .filter(cat->cat.getState().equals(state))
                .collect(Collectors.toList());
    }
    
}
