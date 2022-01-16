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
    
    private Category findCategory(Integer id_category){ 
        return this.categoryRepository.findAll().stream()
                .filter(cat -> cat.getId() == id_category)
                .findFirst()
                 .get();
    }

    public String insert(Product product) {
        String sql = "INSERT INTO product (name, brand, price, stock, id_category, state) VALUES('"
                +product.getName()+"','"+product.getBrand()+"',"
                +product.getPrice()+","+product.getStock()+","
                +product.getCategory().getId()+","
                +Commons.BooleanToInteger(product.getState()) +");";
        
        return GestorBd.execute(sql);
    }

    public String update(Product product) {
        String sql = "UPDATE product SET name = '"+product.getName()+"', "
                + "brand = '"+product.getBrand()+"', "
                + "price = "+product.getPrice()+", "
                + "stock = "+product.getStock()+", "
                + "id_category = "+product.getCategory().getId() 
                + " WHERE id = "+product.getId();
        
        return GestorBd.execute(sql);
    }
    
    public String changeState(int id, Boolean state) {
        String sql = "UPDATE product SET "
                + "state = "+Commons.BooleanToInteger(state) 
                + " WHERE id = "+id;
        return GestorBd.execute(sql);
    }
    
    public List<Product> findAll() {
        List<Product> findAll = new ArrayList<>();
        List list = GestorBd.findAll("SELECT * FROM product;");
        
        if (!list.isEmpty()) { 
            for (Object object : list) {
                Object[] row = (Object[]) object;
                
                Integer id = Integer.parseInt(row[0].toString());
                String name = row[1].toString();
                String brand = row[2].toString();
                Double price = Double.parseDouble(row[3].toString());
                Integer stock = Integer.parseInt(row[4].toString());
                
                Integer id_category = Integer.parseInt(row[5].toString());
                Category category = this.findCategory(id_category);
                
                Boolean state = Commons.IntegerToBoolean(Integer.parseInt(row[6].toString()));
                
                Product product = Product.builder()
                        .id(id)
                        .name(name)
                        .brand(brand)
                        .price(price)
                        .stock(stock)
                        .category(category)
                        .state(state)
                        .build();
                
                findAll.add(product);
            }
        } 
        return findAll;
    }
}
