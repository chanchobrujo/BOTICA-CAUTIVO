/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules;

import services.rolService;
import services.userService;
import util.Commons;
import util.SenderMail;

/**
 *
 * @author kpalmall
 */
public class ModuleUser {
    
    private userService userService;
    private rolService rolService;

    public ModuleUser() { 
        userService = new userService();
        rolService = new rolService();
    }
    
    
    public String saveUser(String firtsname, String lastname, String email, String role) throws Exception{
        String msg = "Rol no encontrado.";
        
        boolean verifyRole = rolService.findByName(role).isPresent(); 
        boolean verifyUser = !userService.findByEmail(email).isPresent();
        
        if (verifyRole) {
            String password = Commons.generatedID(); 
            System.err.println(SenderMail.assingPassword1(email, password));
            
            msg = verifyUser ? this.userService
                    .save(firtsname, lastname, email, password, rolService.findByName(role).get()) 
                    : "Email ya registrado.";
        } 
        return msg;
    }
    
}
