/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules;

import entities.Rol;
import entities.User;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import services.rolService;
import services.userService; 
import util.Commons;
import util.SenderMail;

/**
 *
 * @author kpalmall
 */
public class moduleAuth { 
    
    private userService userService;
    private rolService rolService;

    public moduleAuth() { 
        userService = new userService();
        rolService = new rolService();
    }
    
    
    public Optional<User> login(String email, String password){ 
        return userService.login(email, password);
    }
    
    public Optional<User> findByEmail(String email){ 
        return userService.findByEmail(email);
    }
    
    public Set<String> findAllRole(){  
        return this.rolService.findAll().stream()
                .map(Rol::getName)
                .collect(Collectors.toSet());
    }
    
    public String updatePassword(String email, String password) throws Exception{
        String msg = Constans.Enums.ErrorMessage.USER_NOTFOUND.getValue();
        if (this.findByEmail(email).isPresent()) {
            SenderMail.alertNewPassword(email);
            msg = userService.setPasswordUser(email, password);
        }
        return msg;
    }
    
    public String restoredPassword(String email) throws Exception{
        String msg = Constans.Enums.ErrorMessage.USER_NOTFOUND.getValue();
        if (this.findByEmail(email).isPresent()) {
            String password = Commons.generatedID(); 
            msg = SenderMail.assingPassword(email, password);
            
            msg = msg.concat(Constans.Constan.space)
                    .concat(this.userService.setPasswordUser(email, password));
        }
        return msg;
    }
    
    public String sendToken(String email) throws Exception{
        String msg = Constans.Enums.ErrorMessage.USER_NOTFOUND.getValue();
        if (this.findByEmail(email).isPresent()) {
            msg = SenderMail.sendToken(email);
        }
        return msg;
        
    }
}
