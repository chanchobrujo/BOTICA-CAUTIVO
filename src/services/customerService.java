/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Customer;
import java.util.List;
import java.util.Objects; 
import java.util.Optional; 
import repository.customerRepository;
import util.Commons;

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
        return this.customerRepository.findAll();
    } 
    
    public Optional<Customer> FindByParam(String value){ 
        String firtsname = value;
        String lastname = value;
        
        String email = value;
        String phone = value;
        
        Integer dni = Commons.StringToInteger(value);
        return customerRepository.findAll()
                .stream() 
                .filter((Customer cus)-> {
                    return Commons.StringEqualString(cus.getFirtsname(), firtsname)
                            || Commons.StringEqualString(cus.getLastname(), lastname)
                            || Commons.StringEqualString(cus.getEmail(), email)
                            || Commons.StringEqualString(cus.getPhone(), phone)
                            || Objects.equals(dni, cus.getDni());
                })
                .findFirst();
    }
}
