/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules;

import entities.User;
import java.util.Optional;
import services.rolService;
import services.userService; 
import util.Commons;
import util.MyFuntions;
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
    
    public Optional<User> findByEmail(String email ){ 
        return userService.findByEmail(email);
    }
    
    public String restoredPassword(String email) throws Exception{
        String msg = Constans.Enums.ErrorMessage.USER_NOTFOUND.getValue();
        if (this.findByEmail(email).isPresent()) {
            String password = Commons.generatedID(); 
            msg = SenderMail.sendMail(email, "Restauración de contraseña", password);
            
            msg = msg.concat(" ").concat(this.userService.setPasswordUser(email, password));
        }
        return msg;
    }
}
