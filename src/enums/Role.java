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
 * @author kpalmall
 */
@Getter 
@AllArgsConstructor
public enum Role { 
    ROLE_ADMIN("ADMINISTRADOR");

    private String value; 
}
