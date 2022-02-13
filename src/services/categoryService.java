/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Category;
import java.util.List;
import java.util.Objects;
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
    
    public String save(Integer id, String name){
        String message = Constans.Enums.ErrorMessage.REPETED_VALUES.getValue(); 
        
        if (this.verifyByName(name)) return message;
        
        Category category = Category.builder()
                .name(name)
                .state(Boolean.TRUE)
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
    
    public String changeState(Integer id){
        String message = Constans.Enums.ErrorMessage.NOTFOUND.getValue();
        
        if (this.findById(id).isPresent()) {
            Boolean state = !this.findById(id).get().getState();
            message = categoryRepository.changeState(id, state); 
        } 
        return message;
    } 
    
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
    
    public List<Category> findAll_States(Boolean state){
        return this.findAll().stream()
                .filter(cat->cat.getState().equals(state))
                .collect(Collectors.toList());
    }
    
    public Optional<Category> findByName(String name){
        return categoryRepository.findAll().stream()
                .filter(cat -> Commons.StringEqualString(cat.getName(), name))
                .findFirst();
    }
    
    private Optional<Category> findById(Integer id){
        Category category = categoryRepository.findById(id);
        return Objects.nonNull(category) ? Optional.of(category) : Optional.empty();
    }
    
    private Boolean verifyByName(String name){ 
        return this.findByName(name).isPresent();
    }
    
}
