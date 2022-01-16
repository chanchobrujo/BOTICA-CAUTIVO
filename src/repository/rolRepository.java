/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entities.Rol;
import java.util.ArrayList;
import java.util.List;
import util.GestorBd;

/**
 *
 * @author kpalmall
 */
public class rolRepository {
    public String insert(Rol rol){
        return GestorBd.execute("INSERT INTO rol (name) VALUES('"+rol.getName()+"');");
    }
    
    public List<Rol> findAll(){ 
        List<Rol> findAll = new ArrayList<>();
        List list = GestorBd.findAll("SELECT * FROM rol;");
        
        if (!list.isEmpty()) { 
            for (Object object : list) {
                Object[] row = (Object[]) object; 
                Integer id = Integer.parseInt(row[0].toString());
                
                Rol role = Rol.builder()
                        .id(id)
                        .name(row[1].toString())
                        .build();
                
                findAll.add(role);
            }
        } 
        return findAll; 
    }
}
