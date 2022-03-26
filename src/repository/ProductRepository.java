/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import Constans.Constan;
import entities.Category;
import entities.Product;
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
public class ProductRepository {
    private static final String SCRIPT_SELECT = "SELECT * FROM product";
    private static final String SCRIPT_FINDALL = SCRIPT_SELECT.concat(Constan.semicolon);
    private static String SCRIPT_FINDBYID (Integer id){
        return SCRIPT_SELECT.concat(" WHERE id = ").concat(id.toString()).concat(Constan.semicolon);
    }
    
    private categoryRepository categoryRepository;
    
    public ProductRepository(){
        categoryRepository = new categoryRepository();
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

    public String addStock(Integer idproduct, Integer quantity) {
        String sql = "UPDATE product SET "  
                + "stock = "+quantity
                + " WHERE id = "+idproduct;
        
        return GestorBd.execute(sql);
    }

    public String updateStock(Integer idproduct, Integer quantity) {
        String sql = "UPDATE product SET "  
                + "stock = (stock - "+ quantity +")"
                + " WHERE id = "+idproduct;
        
        return GestorBd.execute(sql);
    }
    
    public String changeState(int id, Boolean state) {
        String sql = "UPDATE product SET "
                + "state = "+Commons.BooleanToInteger(state) 
                + " WHERE id = "+id;
        return GestorBd.execute(sql);
    }
    
    public List<Product> findAll() {
        List list = GestorBd.findAll(SCRIPT_FINDALL);
        List<Product> findAll = new ArrayList<>(); 
        
        if (Commons.collectionNonEmptyOrNull(list))
            list.stream()
                    .map(mapper -> {
                        Object[] row = (Object[]) mapper;
                        Integer id_category = Integer.parseInt(row[5].toString());
                        Category category = this.categoryRepository.findById(id_category);
                        
                        Product product = Mapper.mapperProduct(mapper);
                        product.setCategory(category);
                        
                        findAll.add(product);
                        return mapper;
                    })
                    .collect(Collectors.toList());  
        
        return findAll;
    }

    public Product findById(Integer id) {
        Product product = null;
        Object[] find = GestorBd.find(SCRIPT_FINDBYID(id));
        
        if (Objects.nonNull(find)) { 
            Integer id_category = Integer.parseInt(find[5].toString());
            Category category = this.categoryRepository.findById(id_category); 
            
            product = Mapper.mapperProduct(find);
            product.setCategory(category);
        }
        return product;
    }
}
