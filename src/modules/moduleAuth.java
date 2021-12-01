/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules;

import util.MyFuntions;

/**
 *
 * @author kpalmall
 */
public class moduleAuth {
    public String login(String user, String password){
        String pass2 = MyFuntions.encryptInSHA1(password);
        return "";
    }
}
