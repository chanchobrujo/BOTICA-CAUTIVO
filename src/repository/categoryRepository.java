/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entities.Category; 
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
 * @author umbke
 */
public class categoryRepository {
    private static final String SCRIPT_SELECT = "SELECT * FROM category";
    private static final String SCRIPT_FINDALL = SCRIPT_SELECT.concat(Constan.semicolon);
    private static String SCRIPT_FINDBYID (Integer id){
        return SCRIPT_SELECT.concat(" WHERE id = ").concat(id.toString()).concat(Constan.semicolon);
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

    public String changeState(Integer id, Boolean state) {
        return GestorBd.execute("UPDATE category SET "
                + "state = "+Commons.BooleanToInteger(state) 
                + " WHERE id = "+id);
    }

    public List<Category> findAll() {
        List list = GestorBd.findAll(SCRIPT_FINDALL);
        List<Category> findAll = new ArrayList<>(); 
        
        if (Commons.collectionNonEmptyOrNull(list)) 
            list.stream()
                    .map(mapper -> findAll.add( Mapper.mapperCategory(mapper) ))
                    .collect(Collectors.toList()); 
        
        return findAll;
    }

    public Category findById(Integer id) {
        Category category = null;
        Object[] find = GestorBd.find(SCRIPT_FINDBYID(id));
        
        if (Objects.nonNull(find)) category = Mapper.mapperCategory(find);
        return category;
    }
}
