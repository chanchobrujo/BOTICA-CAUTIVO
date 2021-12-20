/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities; 

import https.Consumer;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kpalmall
 */
public class Customer {
    private Integer id;
    private String firtsname;
    private String lastname;
    private Integer dni;
    private String email;
    private String phone;

    public Customer() {
    } 

    public Customer(Integer dni, String email, String phone) { 
        Map<String, String> map = new HashMap<>();
        
        try {
            map = Consumer.reniec_findPerson(dni.toString()).get();
            
            this.firtsname = map.get("nombre");
            this.lastname = map.get("apellidos"); 
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } 
        
        this.dni = dni;
        this.email = email;
        this.phone = phone;
    }

    public Customer(Integer id, String firtsname, String lastname, Integer dni, String email, String phone) {
        this.id = id;
        this.firtsname = firtsname;
        this.lastname = lastname;
        this.dni = dni;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirtsname() {
        return firtsname;
    }

    public void setFirtsname(String firtsname) {
        this.firtsname = firtsname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    
    
}
