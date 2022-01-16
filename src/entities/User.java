/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;  

import enums.Constans;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author kpalmall
 */
@Getter
@Setter 
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User { 
    private int id;
    private String firtsname;
    private String lastname;
    private String email;
    private String password; 
    private Rol role;
    private Boolean state;

    public User(String firtsname, String lastname, String email, Rol role) {
        this.firtsname = firtsname;
        this.lastname = lastname;
        this.email = email;
        this.password = Constans.empty;
        this.role = role;
        this.state = true;
    }
 
}
