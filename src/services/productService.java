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
    
    public String save(String name, String brand, Double price, Integer stock, String category){
        Optional<Category> cat = this.findByName_Categories(category);
        
        if ( (Commons.DoublesIsEmpty(price, Double.parseDouble(stock.toString())) 
                || Commons.StringsIsEmpty(name, brand)) 
                || !cat.isPresent() ) {
            return enums.Messages.INCORRECT_VALUES.getValue();
        } else {
            if (this.verifyByNameOrBrand(name, brand)) {
                return productRepository
                        .insert(new Product(name, brand, price, stock, cat.get()));
            } else {
                return enums.Messages.REPETED_VALUES.getValue();
            } 
        }
    }
    
    public Boolean verifyByNameOrBrand(String name, String brand){
        return this.findAll().stream()
                .filter(p->p.getBrand().equals(brand) 
                        && p.getName().equals(name))
                .count() == 0;
    }
    
    public List<Product> findAll(){
        return productRepository.findAll();
    }
}
