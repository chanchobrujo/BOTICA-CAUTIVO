/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entities.Category;
import entities.Product;
import java.util.ArrayList;
import java.util.List;
import util.Commons;
import util.GestorBd;

/**
 *
 * @author kpalmall
 */
public class productRepository {
    private categoryRepository categoryRepository;
    
    public productRepository(){
        categoryRepository = new categoryRepository();
    }

    public String insert(Product product) {
        return GestorBd.execute("INSERT INTO product (name, brand, price, stock, id_category, state) "
                + "VALUES('"+product.getName()+"','"+product.getBrand()+"',"+product.getPrice()+","+product.getStock()
                + "',"+product.getCategory().getId()+","+Commons.BooleanToInteger(product.getState()) +");");
    }
    
    public List<Product> findAll() {
        List<Product> findAll = new ArrayList<>();
        List list = GestorBd.findAll("SELECT * FROM product;");
        if (list.isEmpty()) {
            findAll = null;
        } else {
            for (Object object : list) {
                Object[] row = (Object[]) object;
                
                Integer id = Integer.parseInt(row[0].toString());
                String name = row[1].toString();
                String brand = row[2].toString();
                Double price = Double.parseDouble(row[3].toString());
                Integer stock = Integer.parseInt(row[4].toString());
                
                Integer id_category = Integer.parseInt(row[6].toString());
                Category category = categoryRepository.findAll().stream()
                        .filter(cat -> cat.getId() == id_category)
                        .findFirst()
                        .get();
                Boolean state = Commons.IntegerToBoolean(Integer.parseInt(row[7].toString()));
                
                Product product = new Product(id, name, brand, price, stock, category, state);
                findAll.add(product);
            }
        }
        return findAll;
    }
}
