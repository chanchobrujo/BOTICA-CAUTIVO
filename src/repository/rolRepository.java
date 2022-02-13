/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import Constans.Constan;
import entities.Rol;
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
public class rolRepository { 
    private static final String SCRIPT_SELECT = "SELECT * FROM rol";
    private static final String SCRIPT_FINDALL = SCRIPT_SELECT.concat(Constan.semicolon);
    private static String SCRIPT_FINDBYID (Integer id){
        return SCRIPT_SELECT.concat(" WHERE id = ").concat(id.toString()).concat(Constan.semicolon);
    }
    
    public String insert(Rol rol){
        return GestorBd.execute("INSERT INTO rol (name) VALUES('"+rol.getName()+"');");
    }
    
    public List<Rol> findAll(){ 
        List list = GestorBd.findAll(SCRIPT_SELECT);
        List<Rol> findAll = new ArrayList<>(); 
        
        if (Commons.collectionNonEmptyOrNull(list)) { 
            list.stream()
                    .map(mapper -> findAll.add( Mapper.mapperRol(mapper) ))
                    .collect(Collectors.toList());   
        } 
        return findAll; 
    }

    public Rol findById(Integer id) {
        Rol rol = null;
        Object[] find = GestorBd.find(SCRIPT_FINDBYID(id));
        
        if (Objects.nonNull(find)) rol = Mapper.mapperRol(find);
        return rol;
    }
}
