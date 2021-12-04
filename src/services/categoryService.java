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
        return categoryRepository.insert(new Category(name));
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
