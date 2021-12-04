/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entities.Category; 
import java.util.ArrayList;
import java.util.List;
import util.GestorBd;

/**
 *
 * @author umbke
 */
public class categoryRepository {

    public String insert(Category category) {
        return GestorBd.execute("INSERT INTO category (name) "
                + "VALUES('"+category.getName()+"');");
    }

    public String update(Category category) {
        return GestorBd.execute("UPDATE category "
                + "SET name = '"+category.getName()+"', state = "+category.getState()+" "
                        + "WHERE id = "+category.getId());
    }

    public List<Category> findAll() {
        List<Category> findAll = new ArrayList<>();
        List list = GestorBd.findAll("SELECT * FROM category;");
        if (list.isEmpty()) {
            findAll = null;
        } else {
            for (Object object : list) {
                Object[] row = (Object[]) object;
                findAll.add(new Category(Integer.parseInt(row[0].toString()), 
                        row[1].toString(), 
                        util.Commons.toBoolean(Integer.parseInt(row[2].toString()))));
            }
        }
        return findAll;
    }
}
