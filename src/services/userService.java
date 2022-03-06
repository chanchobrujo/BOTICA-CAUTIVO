/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Rol;
import entities.User;
import java.util.List;
import java.util.Objects;
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
    
    public Optional<User> findById(Integer id){ 
        User user = this.userRepository.findById(id);
        return Objects.nonNull(user) ? Optional.of(user) : Optional.empty();
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
    
    public String setPasswordUser(String email, String newpassword){
        this.findByEmail(email).ifPresent(consumer -> {
            consumer.setPassword(MyFuntions.encryptInSHA1(newpassword)); 
            this.userRepository.update(consumer);
        });
        return Constans.Constan.empty;
    }
    
    public String save(String firtsname, String lastname, String email, String password, Rol role){
        User user = User.builder()
                .firtsname(firtsname)
                .lastname(lastname)
                .email(email)
                .role(role)
                .state(Boolean.TRUE)
                .password(MyFuntions.encryptInSHA1(password))
                .build();
        this.userRepository.insert(user);
        return "Usuario registrado.";
    }
}
