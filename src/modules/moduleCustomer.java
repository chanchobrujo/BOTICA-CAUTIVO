/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules;

import entities.Customer;
import enums.ErrorMessage;
import java.util.List;
import java.util.Optional;
import services.customerService;
import util.Commons;

/**
 *
 * @author chanchobrujo
 */
public class moduleCustomer {
    
    private customerService customerService; 

    public moduleCustomer() {
        customerService = new customerService(); 
    }
    
    public String saveCustomer(Integer id, String firtsname, String lastname, 
            Integer dni, String email, String phone){  
        
        boolean verify = Commons.StringsIsEmpty(firtsname, lastname, email, phone); 
        
        return verify ? ErrorMessage.DATA_VOID.getValue() 
                : this.customerService.save(id, firtsname, lastname, dni, email, phone);
    } 
    
    public Customer searchCustomer(String value){
        Optional<Customer> findCustomer = this.customerService.FindByParam(value); 
        return findCustomer.isPresent() ? findCustomer.get() : null;
    } 
    
    public List<Customer> findAll(){
        return this.customerService.findAll();
    }  
}
