/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entities.Rol;
import entities.User;
import java.util.ArrayList;
import java.util.List;
import util.Commons;
import util.GestorBd;

/**
 *
 * @author kpalmall
 */
public class userRepository {

    private rolRepository rolRepository;

    public userRepository() {
        rolRepository = new rolRepository();
    }
    
    public Rol findRole(String id){
        return this.rolRepository.findAll()
                .stream()
                .filter(rol -> rol.getId() == Integer.parseInt(id))
                .findFirst()
                .get();
    }

    public List<User> findAll() { 
        List<User> findAll = new ArrayList<>();
        List list = GestorBd.findAll("SELECT * FROM user;");
        if (!list.isEmpty()) { 
            for (Object object : list) {
                Object[] row = (Object[]) object;
                Integer id = Integer.parseInt(row[0].toString());
                boolean state = Commons.IntegerToBoolean(Integer.parseInt(row[6].toString()));
                
                User user = User.builder()
                        .id(id)
                        .firtsname(row[1].toString())
                        .lastname(row[2].toString())
                        .email(row[3].toString())
                        .password(row[4].toString())
                        .role(this.findRole(row[5].toString()))
                        .state(state)
                        .build();
                
                findAll.add(user);
            }
        } 
        return findAll;
    }
}
