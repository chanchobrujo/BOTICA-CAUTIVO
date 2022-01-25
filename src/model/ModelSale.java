/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
public class ModelSale {
    private String id;
    
    private String date;
    private String time;
    
    private Double subtotal;
    private Double desc;
    private Double total; 
    
    private String user;
    private String customer;
    
}
