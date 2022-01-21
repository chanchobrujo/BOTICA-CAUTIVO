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
import java.util.stream.Collectors;
import util.Commons;
import util.GestorBd;
import util.Mapper;

/**
 *
 * @author kpalmall
 */
public class userRepository {

    private static final List list = GestorBd.findAll("SELECT * FROM user;");

    private rolRepository rolRepository;

    public userRepository() {
        rolRepository = new rolRepository();
    }

    public Rol findRole(String id) {
        return this.rolRepository.findAll()
                .stream()
                .filter(rol -> rol.getId() == Integer.parseInt(id))
                .findFirst()
                .get();
    }

    public List<User> findAll() {
        List<User> findAll = new ArrayList<>();

        if (Commons.collectionNonEmptyOrNull(list)) {
            list.stream()
                    .map(mapper -> {
                        User user = Mapper.mapperUser(mapper);

                        Object[] row = (Object[]) mapper;
                        user.setRole(this.findRole(row[5].toString()));

                        findAll.add(user);
                        return mapper;
                    })
                    .collect(Collectors.toList());
        }
        return findAll;
    }
}
