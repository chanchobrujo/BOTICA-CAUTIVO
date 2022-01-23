/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entities.Details;  
import enums.AlertMessage; 
import java.util.List;
import util.GestorBd;

/**
 *
 * @author kpalmall
 */
public class detailsRepository {
    
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
    
}
