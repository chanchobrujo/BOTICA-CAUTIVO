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
    
    private boolean VerifyRpetedData(Integer dni, String email, String phone){
        return this.customerRepository.findAll()
                .stream()
                .filter(customer -> customer.getDni().equals(dni)
                    || Commons.StringEqualString(customer.getEmail(), email)
                    || Commons.StringEqualString(customer.getPhone(), phone))
                .findFirst()
                .isPresent();
    } 
    
    public String save(Integer id, String firtsname, String lastname, 
            Integer dni, String email, String phone){
        
        String message = enums.ErrorMessage.REPETED_VALUES.getValue(); 
        boolean verifyRepetedData = this.VerifyRpetedData(dni, email, phone);
        
        if (verifyRepetedData) return message;
        
        Customer customer = Customer.builder()
                .firtsname(firtsname)
                .lastname(lastname)
                .dni(dni)
                .email(email)
                .phone(phone)
                .build();
        
        switch(id){
            case 0:  
                message = this.customerRepository.insert(customer);
                break;
            default:  
                customer.setId(id);
                message = this.customerRepository.update(customer);
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
