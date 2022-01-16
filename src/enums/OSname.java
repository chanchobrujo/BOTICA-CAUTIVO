/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author chanchobrujo
 */
@Getter 
@AllArgsConstructor
public enum OSname {
    WINDOWS("\\bd\\db.db"),
    LINUX("LINUX","/bd/db.db");
    
    private String name;
    private String src; 

    private OSname(String src) { 
        this.src = src;
    }  
}
