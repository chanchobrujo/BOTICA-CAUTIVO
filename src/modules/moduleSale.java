/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules;

import entities.Customer;
import entities.Details; 
import entities.Sale;
import entities.User;
import Constans.Enums.ErrorMessage;
import java.util.List; 
import java.util.Objects;
import java.util.Optional; 
import java.util.stream.Collectors; 
import model.ModelDetail;
import model.ModelSale;
import services.customerService;
import services.saleService;
import services.userService;
import util.Commons;

/**
 *
 * @author kpalmall
 */ 
public class moduleSale {
    private final saleService saleService;
    
    private final userService userService;
    private final customerService customerService;  
    
    public moduleSale(Double pordesc){ 
        saleService = new saleService();
        userService = new userService();
        customerService = new customerService();
        this.saleService.newSale(pordesc); 
    }
    
    public void AddProductToCart(int id, int cantidad){
        this.saleService.addLine(id, cantidad); 
    }
    
    public Sale viewDetails(){
        return this.saleService.viewDetails();
    }
    
    public String confirmSale(Integer iduser, Integer idCustomer){
        String message = ErrorMessage.NOTFOUND.getValue();
        
        List<Details> cart = this.viewDetails().getCart();
        boolean verifyCollection = Commons.collectionNonEmptyOrNull(cart); 
        
        Optional<User> findUser = userService.findById(iduser);
        Optional<Customer> findCustomer = customerService.findAll().stream()
                .filter(customer -> Objects.equals(customer.getId(), idCustomer))
                .findFirst();
        boolean verifyUser = findUser.isPresent(); 
        boolean verifyCustomer = findCustomer.isPresent(); 
                
        if (verifyCollection && verifyUser && verifyCustomer) {
            this.viewDetails().setUser(findUser.get());
            this.viewDetails().setCustomer(findCustomer.get());
            
            message = this.saleService.saveOrder(this.viewDetails()); 
        }
        
        return message;
    }
    
    public List<ModelSale> findAll() {
        return this.saleService.findAll();
    }
    
    public List<ModelSale> findAllByCustomer(String customer) {
        return this.saleService.findAll().stream()
                .filter(sale -> Commons.StringEqualString(sale.getCustomer(), customer))
                .collect(Collectors.toList());
    }
    
    public List<ModelSale> findAllByUser(String user) {
        return this.saleService.findAll().stream()
                .filter(sale -> Commons.StringEqualString(sale.getUser(), user))
                .collect(Collectors.toList());
    }
    
    public List<ModelSale> findAllByDate(String date) {
        return this.saleService.findAll().stream()
                .filter(sale -> Commons.StringEqualString(sale.getDate(), date))
                .collect(Collectors.toList());
    }
    
    public List<ModelDetail> findAllDetails(String id) {
        return this.saleService.findAllDetails(id);
    }
}
