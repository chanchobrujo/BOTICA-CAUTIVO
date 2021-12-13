/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Category;
import entities.Product; 
import java.util.List; 
import java.util.Optional;
import repository.categoryRepository;
import repository.productRepository;
import util.Commons;

/**
 *
 * @author kpalmall
 */
public class productService {
    
    private productRepository productRepository;
    private categoryRepository categoryRepository;
    
    public productService(){
        productRepository = new productRepository();
        categoryRepository = new categoryRepository();
    }
    
    private Optional<Category> findByName_Categories(String name){
        return categoryRepository.findAll().stream()
                .filter(cat -> cat.getName().equals(name))
                .findFirst();
    } 
    
    public String save(int id, String name, String brand, Double price, 
            Integer Stock, String category){
        
        Optional<Category> _category = this.findByName_Categories(category);
        String message = enums.Messages.REPETED_VALUES.getValue();
        Boolean verify = this.verifyByNameOrBrand(name, brand);
        
        if (!verify) return message;
        
        switch(id){
            case 0: 
                message = this.productRepository.insert(new 
                        Product(name, brand, price, Stock, _category.get()) );
                break;
            default: 
                message = this.productRepository.update(new 
                        Product(id, name, brand, price, Stock, _category.get()));
                break;
        }
        return message;
    }
    
    public String changeState(int id){
        String message = enums.Messages.NOTFOUND.getValue();
        
        if (this.findById(id).isPresent()) {
            Boolean state = !this.findById(id).get().getState();
            message = productRepository.changeState(id, state);
        } 
        return message;
    } 
    
    public Boolean verifyByNameOrBrand(String name, String brand){
        return this.findAll().stream()
                .filter(p->p.getBrand().equals(brand) 
                        && p.getName().equals(name))
                .count() == 0;
    }
    
    public Optional<Product> findById(int id){
        return this.findAll().stream()
                .filter(pro->pro.getId() == id)
                .findFirst();
    }
    
    public List<Product> findAll(){
        return productRepository.findAll();
    }
}
