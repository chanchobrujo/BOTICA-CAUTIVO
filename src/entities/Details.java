/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author kpalmall
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Details { 
    private Integer id;
    private String id_sale;
    private Product product;
    private Integer quantity;

    public Details(String id_sale, Product product, Integer quantity) { 
        this.id_sale = id_sale;
        this.product = product;
        this.quantity = quantity;
    } 
    
    public Double getImport(){
        return product.getPrice() * this.quantity;
    }  
    
}
