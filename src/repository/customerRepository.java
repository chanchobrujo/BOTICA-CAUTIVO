/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entities.Customer; 
import Constans.Constan;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import util.Commons;
import util.GestorBd;
import util.Mapper;

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

    public String update(Customer customer) { 
        String sql =  "UPDATE customer SET "
                .concat("firtsname='").concat(customer.getFirtsname()).concat("', ")
                .concat("lastname='").concat(customer.getLastname()).concat("', ")
                .concat("dni=").concat(customer.getDni()+Constan.empty).concat(", ")
                .concat("email='").concat(customer.getEmail()).concat("', ")
                .concat("phone='").concat(customer.getPhone()).concat("' ")
                .concat("' WHERE id=").concat(customer.getId()+"");
        return GestorBd.execute(sql);
    }

    public List<Customer> findAll() {
        List list = GestorBd.findAll("SELECT * FROM customer;");
        List<Customer> findAll = new ArrayList<>(); 
        
        if (Commons.collectionNonEmptyOrNull(list))   
            list.stream()
                    .map(mapper -> findAll.add(Mapper.mapperCustomer(mapper)))
                    .collect(Collectors.toList()); 
          
        return findAll;
    }
}
