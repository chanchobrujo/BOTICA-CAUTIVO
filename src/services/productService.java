/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Category;
import entities.Product;
import java.sql.Blob;
import java.util.List;
import java.util.Objects;
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
                .filter(cat -> cat.getName().equals(name))  //.map(Category::getName)
                .findFirst();
    }
    
    public String save(String name, String brand, Double price, Integer stock, Blob image, String category){
        Optional<Category> cat = this.findByName_Categories(category);
        if ( !Commons.StringIsEmpty(name) 
                && cat.isPresent() ) {
            return productRepository.insert( new Product(name, brand, price, stock, image, cat.get()) );
        } else {
            return enums.Messages.INCORRECT_VALUES.getValue();
        }
    }
    
    public List<Product> findAll(){
        return productRepository.findAll();
    }
}
