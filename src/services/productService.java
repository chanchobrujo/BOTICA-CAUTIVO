/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Product;
import java.util.List;
import repository.categoryRepository;
import repository.productRepository;

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
    
    public List<Product> findAll(){
        return productRepository.findAll();
    }
}
