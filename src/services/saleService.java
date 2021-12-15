/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Product;
import entities.Sale;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author chanchobrujo
 */
public class saleService {
    private userService userService;
    private productService productService;
    
    private Sale sale = new Sale();

    public saleService() {
        userService = new userService();
        productService = new productService();
    } 
    
    public void newSale(int idUser, Double porDes){
        this.sale.setUser(userService.findById(idUser));
        this.sale.setPordes(porDes); 
    }
    
    public void removeProduct(int id){
        this.sale.removeProduct(id);
    }
    
    public void addLine(int id, int cantidad) {
        this.sale.addProduct(productService.findById(id).get(), cantidad);  
    }
    
    public List<Product> findAllProducts() {
        return productService.findAll().stream()
                .filter(Product::getState)
                .collect(Collectors.toList());
    }
    
    public void clearCart() {
        this.sale.clearCart();
    }
    
    public Sale viewDetails() {
        return this.sale;
    }

}
