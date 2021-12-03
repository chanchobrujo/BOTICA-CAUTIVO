/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

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

    public List<User> findAll() { 
        List<User> findAll = new ArrayList<>();
        List list = GestorBd.findAll("SELECT * FROM user;");
        if (list.isEmpty()) {
            findAll = null;
        } else {
            for (Object object : list) {
                Object[] row = (Object[]) object;
                findAll.add(new User(Integer.parseInt(row[0].toString()),
                        row[1].toString(), row[2].toString(),
                        row[3].toString(), row[4].toString(),
                        rolRepository.findAll()
                                .stream()
                                .filter(rol -> rol.getId() == Integer.parseInt(row[5].toString()))
                                .findFirst()
                                .get(),
                        Commons.toBoolean(Integer.parseInt(row[6].toString()))));
            }

        }
        return findAll;
    }
}
