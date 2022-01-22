/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.User;
import java.util.List;
import java.util.Optional;
import repository.rolRepository;
import repository.userRepository;
import util.MyFuntions;

/**
 *
 * @author kpalmall
 */
public class userService {
    private rolRepository rolRepository;
    private userRepository userRepository;
    
    public userService(){
        rolRepository = new rolRepository();
        userRepository = new userRepository();
    }
    
    public List<User> findAll(){
        return userRepository.findAll();
    }
    
    public Optional<User> findById(int id){
        return userRepository.findAll().stream()
                .filter(u->u.getId() == id)
                .findFirst();
    }
    
    public Optional<User> login(String email, String password){
        return this.findAll().stream()
                .filter(user->user.getEmail().equals(email))
                .filter(user->user.getPassword().equals( MyFuntions.encryptInSHA1(password) ))
                .filter(user->user.getState())
                .findFirst();
    }
    
    public Optional<User> findByEmail(String email){
        return this.findAll().stream()
                .filter(user->user.getEmail().equals(email)) 
                .filter(user->user.getState())
                .findFirst();
    }
}
