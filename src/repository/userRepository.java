/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entities.User;
import Constans.Constan; 
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import util.Commons;
import util.GestorBd;
import util.Mapper;

/**
 *
 * @author kpalmall
 */
public class userRepository {
    private static final String SCRIPT_SELECT = "SELECT * FROM user";
    private static final String SCRIPT_FINDALL = SCRIPT_SELECT.concat(Constan.semicolon);
    private static String SCRIPT_FINDBYID (Integer id){
        return SCRIPT_SELECT.concat(" WHERE id = ").concat(id.toString()).concat(Constan.semicolon);
    }

    private rolRepository rolRepository;

    public userRepository() {
        rolRepository = new rolRepository();
    }

    public String insert(User user) { 
        String sql = "INSERT INTO user (firtsname, lastname, email, password, id_role, state) VALUES "
                + "('"+user.getFirtsname()+"', '"+user.getLastname()+"', '"+user.getEmail()+"', '"+user.getPassword()+"', "
                + user.getRole().getId() +", "+ Commons.BooleanToInteger(user.getState()) +")";
        return GestorBd.execute(sql);
    }
    

    public String update(User user) {
        String sql = "UPDATE user SET password = '"+user.getPassword()+"' WHERE id = "+user.getId();
        return GestorBd.execute(sql);
    }
    
    public List<User> findAll() {
        List list = GestorBd.findAll(SCRIPT_SELECT);
        List<User> findAll = new ArrayList<>();

        if (Commons.collectionNonEmptyOrNull(list)) {
            list.stream()
                    .map(mapper -> { 
                        Object[] row = (Object[]) mapper;
                        Integer id_role = Integer.parseInt(row[5].toString());
                        
                        User user = Mapper.mapperUser(mapper);
                        user.setRole(this.rolRepository.findById(id_role));

                        findAll.add(user);
                        return mapper;
                    })
                    .collect(Collectors.toList());
        }
        return findAll;
    }

    public User findById(Integer id) {
        User user = null;
        Object[] find = GestorBd.find(SCRIPT_FINDBYID(id));
        
        if (Objects.nonNull(find)) {
            Integer id_role = Integer.parseInt(find[5].toString());
            user = Mapper.mapperUser(find);
            user.setRole(this.rolRepository.findById(id_role));
        }
        return user;
    }
}
