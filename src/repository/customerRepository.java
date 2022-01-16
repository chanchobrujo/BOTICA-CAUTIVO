/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entities.Customer; 
import java.util.ArrayList;
import java.util.List;
import util.Commons;
import util.GestorBd;

/**
 *
 * @author kpalmall
 */
public class customerRepository {

    public String insert(Customer customer) {
        String sql = "INSERT INTO customer (firtsname, lastname, dni, email, phone) VALUES"
                + "('"+customer.getFirtsname()+"','"+customer.getLastname()+"',"
                +customer.getDni()+",'"+customer.getEmail()+"','"+customer.getPhone()+"')";
        return GestorBd.execute(sql);
    }

    public List<Customer> findAll() {
        List<Customer> findAll = new ArrayList<>();
        List list = GestorBd.findAll("SELECT * FROM customer;");
        
        if (!list.isEmpty()) { 
            for (Object object : list) {
                Object[] row = (Object[]) object;
                Integer id = Commons.StringToInteger(row[0].toString());
                Integer dni = Commons.StringToInteger(row[3].toString());
                
                Customer customer = Customer.builder()
                        .id(id)
                        .firtsname(row[1].toString())
                        .lastname(row[2].toString())
                        .dni(dni)
                        .email(row[4].toString())
                        .phone(row[5].toString())
                        .build();
                
                findAll.add(customer);
            }
        }  
        return findAll;
    }
}
