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
import java.util.Objects;
import java.util.stream.Collectors;
import util.Commons;
import util.GestorBd;
import util.Mapper;

/**
 *
 * @author kpalmall
 */
public class customerRepository { 
    private static final String SCRIPT_SELECT = "SELECT * FROM customer";
    private static final String SCRIPT_FINDALL = SCRIPT_SELECT.concat(Constan.semicolon);
    private static String SCRIPT_FINDBYID (Integer id){
        return SCRIPT_SELECT.concat(" WHERE id = ").concat(id.toString()).concat(Constan.semicolon);
    }

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
        List list = GestorBd.findAll(SCRIPT_FINDALL);
        List<Customer> findAll = new ArrayList<>(); 
        
        if (Commons.collectionNonEmptyOrNull(list))   
            list.stream()
                    .map(mapper -> findAll.add(Mapper.mapperCustomer(mapper)))
                    .collect(Collectors.toList()); 
          
        return findAll;
    }

    public Customer findById(Integer id) {
        Customer customer = null;
        Object[] find = GestorBd.find(SCRIPT_FINDBYID(id));
        
        if (Objects.nonNull(find)) customer = Mapper.mapperCustomer(find);
        return customer;
    }
}
