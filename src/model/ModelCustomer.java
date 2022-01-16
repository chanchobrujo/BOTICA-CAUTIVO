/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author chanchobrujo
 */
public class ModelCustomer {
    private Integer id;
    private String firtsname;
    private String lastname;
    private Integer dni;
    private String email;
    private String phone;

    public ModelCustomer() {
    }
     
    public ModelCustomer(Integer id, String firtsname, String lastname, Integer dni, String email, String phone) {
        this.id = id;
        this.firtsname = firtsname;
        this.lastname = lastname;
        this.dni = dni;
        this.email = email;
        this.phone = phone;
    } 

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
