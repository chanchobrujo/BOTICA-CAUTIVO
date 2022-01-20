/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entities.Category; 
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import util.Commons;
import util.GestorBd;

/**
 *
 * @author umbke
 */
public class categoryRepository {
    private static final List list = GestorBd.findAll("SELECT * FROM category;");
    
    private Category mapperCategory(Object obj){
        Object[] category = (Object[]) obj;
        Integer id = Commons.StringToInteger(category[0].toString());
        boolean state = Commons.IntegerToBoolean(Integer.parseInt(category[2].toString()));
        
        return Category.builder()
                .id(id)
                .name(category[1].toString())
                .state(state)
                .build();
    }

    public String insert(Category category) {
        return GestorBd.execute("INSERT INTO category (name, state) "
                + "VALUES('"+category.getName()+"',"+Commons.BooleanToInteger(category.getState())+");");
    }

    public String update(Category category) {
        return GestorBd.execute("UPDATE category "
                + "SET name = '"+category.getName()+"' "
                + "WHERE id = "+category.getId());
    }

    public String changeState(int id, Boolean state) {
        return GestorBd.execute("UPDATE category SET "
                + "state = "+Commons.BooleanToInteger(state) 
                + " WHERE id = "+id);
    }

    public List<Category> findAll() {
        List<Category> findAll = new ArrayList<>(); 
        
        if (Commons.collectionNonEmptyOrNull(list)) { 
            list.stream()
                    .map(mapper -> findAll.add(this.mapperCategory(mapper)))
                    .collect(Collectors.toList()); 
        }   
        return findAll;
    }
}
