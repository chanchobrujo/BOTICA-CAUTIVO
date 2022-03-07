/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Constans.Enums;

import java.util.Optional; 
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import util.Commons;

/**
 *
 * @author kpalmall
 */
@Getter  
@AllArgsConstructor
@NoArgsConstructor
public enum Role {  
    
    ROLE_ADMIN("ADMINISTRADOR", true, true, true, true),
    ROLE_USER("CAJERO", false, false, false, false);   

    private String value;
    private boolean menuMantenimiento;
    private boolean menuOperaciones;
    private boolean menuUsuarios;
    private boolean menuReportes;
    
    public static Optional<Role> findRole(String value){
        return Stream.of(values())
                .filter(role -> Commons.StringEqualString(role.getValue(), value))
                .findFirst(); 
    } 
}
