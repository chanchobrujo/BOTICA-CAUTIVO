/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entities.Rol;
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
public class rolRepository {
    private static final List list = GestorBd.findAll("SELECT * FROM rol;");
    
    public String insert(Rol rol){
        return GestorBd.execute("INSERT INTO rol (name) VALUES('"+rol.getName()+"');");
    }
    
    public List<Rol> findAll(){ 
        List<Rol> findAll = new ArrayList<>(); 
        
        if (Commons.collectionNonEmptyOrNull(list)) { 
            list.stream()
                    .map(mapper -> findAll.add( Mapper.mapperRol(mapper) ))
                    .collect(Collectors.toList());   
        } 
        return findAll; 
    }
}
