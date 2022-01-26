/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entities.Details;  
import entities.Product;
import enums.AlertMessage; 
import enums.Constans;
import java.util.ArrayList;
import java.util.List; 
import java.util.Objects;
import java.util.Optional;
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
    
    private Optional<Product> findProductByID(Integer id){
        return this.productRepository.findAll()
                .stream()
                .filter(p -> Objects.equals(p.getId(), id))
                .findFirst();
    }
    
    private String findNameProduct(Integer id){
        Optional<Product> findProduct = this.findProductByID(id);
        boolean verify = findProduct.isPresent();
        
        return verify ? 
                findProduct.get()
                        .getName()
                        .concat(Constans.double_point)
                        .concat(findProduct.get().getBrand())
                        .concat(Constans.double_point)  
                        .concat(findProduct.get().getCategory().getName()) 
                : Constans.empty;
    }
    
    private Double findPriceProduct(Integer id){ 
        Optional<Product> findProduct = this.findProductByID(id);
        boolean verify = findProduct.isPresent();
        
        return verify ? findProduct.get().getPrice() : 0.0;
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
