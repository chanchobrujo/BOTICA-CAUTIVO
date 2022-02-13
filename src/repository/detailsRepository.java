/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entities.Details;  
import entities.Product;
import Constans.Enums.AlertMessage;
import Constans.Constan;
import java.util.ArrayList;
import java.util.List; 
import java.util.Objects;
import model.ModelDetail;
import util.Commons;
import util.GestorBd;

/**
 *
 * @author kpalmall
 */
public class detailsRepository {
    private productRepository productRepository;
    
    public detailsRepository(){
        productRepository = new productRepository();
    }
    
    private String findNameProduct(Integer id){
        Product findProduct = this.productRepository.findById(id); 
        
        return Objects.nonNull(findProduct) ? 
                findProduct.getName()
                        .concat(Constan.double_point)
                        .concat(findProduct.getBrand())
                        .concat(Constan.double_point)  
                        .concat(findProduct.getCategory().getName()) 
                : Constan.empty;
    }
    
    private Double findPriceProduct(Integer id){ 
        Product findProduct = this.productRepository.findById(id);
        
        return Objects.nonNull(findProduct) ? findProduct.getPrice() : 0.0;
    }
    
    private String grabarDetalle(Details details) {
        String sql = "INSERT INTO detail_sale_product "
                .concat("(id_sale, id_product, quantity) ")
                .concat("VALUES("+details.getId_sale()+", "
                        +details.getProduct().getId()+", "
                        +details.getQuantity()+");"); 
        return GestorBd.execute(sql);
    }
    
    public String grabarDetalles(List<Details> cart){
        cart.stream().forEach(this::grabarDetalle);
        return AlertMessage.EXECUTE_SUCCESS.getValue();
    }
    
    public List<ModelDetail> findAll(String id) {
        List list = GestorBd.findAll("SELECT id_product, quantity FROM detail_sale_product where id_sale="+ id);
        List<ModelDetail> findAll = new ArrayList<>();
        
        if (Commons.collectionNonEmptyOrNull(list)) {
            for (Object object : list) { 
                Object[] row = (Object[]) object;
                
                Integer idproduc = Commons.StringToInteger(row[0].toString());
                Integer quantity = Commons.StringToInteger(row[1].toString());
                Double price = this.findPriceProduct(idproduc);
                
                ModelDetail model = ModelDetail
                        .builder()
                        .product(this.findNameProduct(idproduc))
                        .product_price(price)
                        .quantity(quantity)
                        ._import(quantity*price)
                        .build();
                
                findAll.add(model);
            }
        } 
        return findAll;  
    }
    
}
