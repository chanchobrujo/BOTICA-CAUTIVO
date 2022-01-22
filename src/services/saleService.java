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
import repository.saleRepository;

/**
 *
 * @author chanchobrujo
 */
public class saleService {
    private final userService userService;
    private final productService productService;
    
    private saleRepository saleRepository;
    
    private final Sale sale = new Sale();

    public saleService() {
        userService = new userService();
        productService = new productService();
        
        saleRepository = new saleRepository();
    } 
    
    public void newSale(Double porDes){ 
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
    
    public String saveOrder(Sale sale){
        return this.saleRepository.grabarVentasParaUsuarioCliente(sale);
    }

}
