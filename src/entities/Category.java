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
public class Category {

    private int id;
    private String name;
    private Boolean state; 

    public Category(String name) {
        this.name = name;
        this.state = true;
    }

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public Category(String name, Boolean state) {
        this.name = name;
        this.state = state;
    } 
}
