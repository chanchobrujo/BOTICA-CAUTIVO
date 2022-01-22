/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList; 
import java.util.List;  
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter; 
import lombok.Setter;
import lombok.ToString;
import util.Commons;

/**
 *
 * @author kpalmall
 */
@Getter
@Setter 
@Builder
@ToString
@AllArgsConstructor
public class Sale {
    private String id;
    private String date;
    private String time;
    private Double subtotal;
    private Double desc;
    private Double total;
    private Boolean state;
    
    private Double pordes;
    
    private User user;
    private Customer Customer;
    
    private List<Details> cart;  
    
    public Sale() {
        this.id = Commons.generatedIdNumber();
        this.date = Commons.generatedDateNow();
        this.time = Commons.generatedTimeNow();
        this.cart = new ArrayList<>();  
        this.state = true;
    }
    
    public Sale(Double subtotal, Double desc, Double total, User user, 
            Customer Customer) { 
        this.subtotal = subtotal;
        this.desc = desc;
        this.total = total;
        this.user = user;
        this.Customer = Customer; 
    }

    public Sale(String id, String date, String time, Double subtotal, Double desc, 
            Double total, Boolean state, User user, Customer Customer) {
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
    
    public void addProduct(Product Product, Integer quantity){     
        Boolean verifyProd = this.cart.stream()
                .filter(d->d.getProduct().getId() == Product.getId())
                .collect(Collectors.toList())
                .isEmpty();   
        
        Boolean verifyEmpty = this.cart.stream() 
                .collect(Collectors.toList())
                .isEmpty();
            
        if (verifyEmpty || verifyProd) {
            this.cart.add(new Details(this.getId(), Product, quantity));
        } else if(!verifyProd){
            
            Details detail = this.cart.stream()
                    .filter(d->d.getProduct().getId() == Product.getId())
                    .findFirst()
                    .get();
            
            Integer quantityO = detail.getQuantity() + quantity; 
            detail.setQuantity(quantityO); 
        }
    }
    
    public void clearCart(){
        this.cart.clear();
    }
    
    public void removeProduct(int id_product){   
        for (int i = 0; i < this.cart.size(); i++) {
            if (this.cart.get(i).getProduct().getId() == id_product) {
                this.cart.remove(i);
            }
        }
    } 
    
    public Double getSubtotal() {
        Double subb = 0.0;
        subb = this.cart.stream()
                .map(details -> details.getImport())
                .reduce(subb, (accumulator, _item) -> accumulator + _item);
        
        return subb;
    } 
    
    public Double getDesc() {
        return this.getSubtotal() * this.getPordes();
    } 
    
    public Double getTotal() {
        return this.getSubtotal() - this.getDesc();
    }   

    public void setPordes(Double pordes) {
        this.pordes =  pordes/100;
    }   
    
}