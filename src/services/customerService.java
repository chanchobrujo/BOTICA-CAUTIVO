/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Customer;
import java.util.List;
import repository.customerRepository;

/**
 *
 * @author kpalmall
 */
public class customerService {
    
    private customerRepository customerRepository;
    
    public customerService(){
        customerRepository = new customerRepository();
    } 
    
    public String save(int id, String name){
        String message = enums.ErrorMessage.REPETED_VALUES.getValue(); 
        
        if (true) return message;
        
        switch(id){
            case 0: 
                //message = this.customerRepository.insert();
                break;
            default:  
                //
                break;
        }
        return message;
    }
    
    public List<Customer> findAll(){
        return customerRepository.findAll();
    }
}
