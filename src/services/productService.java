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
import java.util.stream.Collectors;
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
        String message = enums.ErrorMessage.REPETED_VALUES.getValue();
        Boolean verify = this.verifyByNameOrBrand(name, brand);
        
        if (!verify) return message;
        
        Product product = Product.builder()
                .name(name)
                .brand(brand)
                .price(price)
                .stock(Stock)
                .category(_category.get())
                .build();
        
        switch(id){
            case 0: 
                message = this.productRepository.insert(product);
                break;
            default: 
                product.setId(id);
                message = this.productRepository.update(product);
                break;
        }
        return message;
    }
    
    public String changeState(int id){
        String message = enums.ErrorMessage.NOTFOUND.getValue();
        
        if (this.findById(id).isPresent()) {
            Boolean state = !this.findById(id).get().getState();
            message = productRepository.changeState(id, state);
        } 
        return message;
    } 
    
    public Boolean verifyByNameOrBrand(String name, String brand){
        return this.findAll().stream()
                .filter(pp -> pp.getBrand().equals(brand))
                .filter(pp -> pp.getName().equals(name))
                .count() == 0;
    }
    
    public Optional<Product> findById(int id){
        return this.findAll().stream()
                .filter(pro -> pro.getId() == id)
                .findFirst();
    }
    
    public List<Product> findAll(){
        return productRepository.findAll();
    }
    
    public List<Product> searchProduct(String value){
        String name = value;
        String brand = value;
        String category = value;
        
        Double price = Commons.StringToDouble(value); 
        Integer stock = Commons.StringToInteger(value);
        
        return productRepository.findAll().stream()
                .filter((Product pr)-> {
                    return pr.getBrand().equals(name) 
                            || Commons.StringEqualString(pr.getName(), name)
                            || Commons.StringEqualString(pr.getBrand(), brand)
                            || Commons.StringEqualString(pr.getCategory().getName(), category) 
                            || pr.getPrice() <= price || pr.getStock() <= stock ; 
                })
                .collect(Collectors.toList());
    }
}
