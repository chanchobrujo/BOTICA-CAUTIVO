/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Category; 
import entities.Product; 

import java.util.List;   
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import repository.categoryRepository;
import repository.ProductRepository;

import util.Commons;

/**
 *
 * @author kpalmall
 */
public class ProductService {
    
    private ProductRepository productRepository;
    private categoryRepository categoryRepository;
    
    public ProductService(){
        productRepository = new ProductRepository();
        categoryRepository = new categoryRepository();
    }
    
    private Optional<Category> findByName_Categories(String name){
        return categoryRepository.findAll().stream()
                .filter(cat -> cat.getName().equals(name))
                .findFirst();
    } 
    
    public String save(int id, String name, String brand, Double price, 
            Integer Stock, String category){
        String message = Constans.Enums.ErrorMessage.REPETED_VALUES.getValue();
        Optional<Category> _category = this.findByName_Categories(category);
        
        Boolean verify = this.verifyByNameOrBrand(name, brand).isPresent(); 
        
        if (verify) return message;
        
        Product product = Product.builder()
                .name(name)
                .brand(brand)
                .price(price)
                .stock(Stock)
                .category(_category.get())
                .state(Boolean.TRUE)
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
        String message = Constans.Enums.ErrorMessage.NOTFOUND.getValue();
        Optional<Product> findP = this.findById(id);
        
        if (findP.isPresent()) {
            Boolean state = !findP.get().getState();
            message = productRepository.changeState(id, state);
        }
        return message;
    } 
    
    public String addStock(Integer id, Integer quantity){
        String message = Constans.Enums.ErrorMessage.NOTFOUND.getValue();
        Optional<Product> findP = this.findById(id);
        
        if (findP.isPresent()) {
            Integer stock = findP.get().getStock() + quantity;
            message = productRepository.addStock(id, stock);
            if (stock >= 10) message = productRepository.changeState(id, Boolean.TRUE);
        }
        return message;
    }
    
    public Optional<Product> verifyByNameOrBrand(String name, String brand){
        return this.findAll().stream()
                .filter(pp -> pp.getBrand().equals(brand))
                .filter(pp -> pp.getName().equals(name))
                .findFirst();
    } 
    
    public Optional<Product> findById(Integer id){
        Product product = this.productRepository.findById(id);
        return Objects.nonNull(product) ? Optional.of(product) : Optional.empty();
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
    
    public List<Product> findAllByStockMin(){
        return this.productRepository.findAll().stream()
                .filter(predicate -> predicate.getStock() <= 10)
                .map(mapper -> {
                    this.productRepository.changeState(mapper.getId(), Boolean.FALSE);
                    return mapper;
                })
                .collect(Collectors.toList());
    }
}