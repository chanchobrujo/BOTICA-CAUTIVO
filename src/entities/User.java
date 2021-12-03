/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;  

/**
 *
 * @author kpalmall
 */
public class User { 
    private int id;
    private String firtsname;
    private String lastname;
    private String email;
    private String password; 
    private Rol role;
    private Boolean state;

    public User() {
    }

    public User(String firtsname, String lastname, String email, Rol role) {
        this.firtsname = firtsname;
        this.lastname = lastname;
        this.email = email;
        this.password = "";
        this.role = role;
        this.state = true;
    }

    public User(int id, String firtsname, String lastname, String email, String password, Rol role, Boolean state) {
        this.id = id;
        this.firtsname = firtsname;
        this.lastname = lastname;
        this.email = email;
        this.password = password; 
        this.role = role;
        this.state = state;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    } 

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Rol getRole() {
        return role;
    }

    public void setRole(Rol role) {
        this.role = role;
    }
    
    
}
