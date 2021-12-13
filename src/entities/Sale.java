/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author kpalmall
 */
public class Sale {
    private String id;
    private Date date;
    private Time time;
    private Double subtotal;
    private Double desc;
    private Double total;
    private Boolean state;
    
    private User user;
    private Customer Customer;

    public Sale() {
    } 
    
    public Sale(Double subtotal, Double desc, Double total, User user, Customer Customer) {
        
        this.subtotal = subtotal;
        this.desc = desc;
        this.total = total;
        this.user = user;
        this.Customer = Customer;
    }

    public Sale(String id, Date date, Time time, Double subtotal, Double desc, Double total, Boolean state, User user, Customer Customer) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.subtotal = subtotal;
        this.desc = desc;
        this.total = total;
        this.state = state;
        this.user = user;
        this.Customer = Customer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getDesc() {
        return desc;
    }

    public void setDesc(Double desc) {
        this.desc = desc;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Customer getCustomer() {
        return Customer;
    }

    public void setCustomer(Customer Customer) {
        this.Customer = Customer;
    }
    
    
}