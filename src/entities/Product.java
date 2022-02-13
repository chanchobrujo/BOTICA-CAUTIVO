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
public class Product {

    private Integer id;
    private String name;
    private String brand;
    private Double price;
    private Integer stock; 
    private Category category;
    private Boolean state; 
    
    public Product(String name, String brand, Double price, Integer stock, 
            Category category) { 
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.stock = stock; 
        this.category = category;
        this.state = true;
    }

    public Product(int id, String name, String brand, Double price, Integer stock, 
            Category category) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.stock = stock; 
        this.category = category; 
    } 
    
}
