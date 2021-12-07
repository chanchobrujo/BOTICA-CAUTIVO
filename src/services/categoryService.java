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
    
    public String save(String name){
        if ( !Commons.isEmpty(name) ) {
            return categoryRepository.insert(new Category(name));
        } else {
            return enums.Constant.INCORRECT_VALUES.getValue();
        }
    }
    
    public String update(int id, String name, Boolean state){
        String message = enums.Constant.NOTFOUND.getValue();
        
        if (this.findById(id).isPresent() || !Commons.isEmpty(name)) {
            if (this.findByName(name).isPresent()) {
                message = enums.Constant.REPETED_VALUES.getValue();
            } else { 
                message = categoryRepository.update(
                        new Category(id, name, state)); 
            }
        } 
        return message;
    } 
    
    public String changeState(int id){
        String message = enums.Constant.NOTFOUND.getValue();
        
        if (this.findById(id).isPresent()) {
            Boolean state = !this.findById(id).get().getState();
            message = categoryRepository.changeState(id, state); 
        } 
        return message;
    } 
    
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
    
    public Optional<Category> findByName(String name){
        return categoryRepository.findAll().stream()
                .filter(cat->cat.getName().equals(name))
                .findFirst();
    }
    
    public Optional<Category> findById(int id){
        return categoryRepository.findAll().stream()
                .filter(rol->rol.getId()==id )
                .findFirst();
    }
    
    public List<Category> findAll_States(Boolean state){
        return this.findAll().stream()
                .filter(cat->cat.getState().equals(state))
                .collect(Collectors.toList());
    }
    
}
