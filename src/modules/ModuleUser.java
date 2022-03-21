/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules;

import Constans.Enums.ErrorMessage;
import entities.User;
import java.util.List;
import java.util.Optional;
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
    
    public List<User> findAllUsers(){
        return userService.findAll();
    }
    
    public String saveUser(String firtsname, String lastname, String email, String role) throws Exception{
        String msg = ErrorMessage.NOTFOUND.getValue();
        
        boolean verify = !Commons.StringsIsEmpty(firtsname, lastname, email, role);
        boolean verifyRole = rolService.findByName(role).isPresent(); 
        boolean verifyUser = !userService.findByEmail(email).isPresent();
        
        if (verify && verifyRole) {
            String password = Commons.generatedID();
            System.out.println(SenderMail.assingPassword1(email, password));
            
            msg = verifyUser ? this.userService
                    .save(firtsname, lastname, email, password, rolService.findByName(role).get()) 
                    : ErrorMessage.REPETED_VALUES.getValue();
        } 
        return msg;
    }
    
    public String stateUser(Integer id){
        String msg = ErrorMessage.NOTFOUND.getValue();
        boolean verifyUser = userService.findById(id).isPresent();
        
        if (verifyUser) return this.userService.updateState(id);
        return msg;
    }
    
    public boolean findByIdAndEmailVerify(Integer id, String email){
        return this.userService.findById(id)
                    .map(mapper -> mapper.getEmail())
                    .orElse(Constans.Constan.empty)
                    .equals(email);
    }
    
    
    
}
