/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules;

import entities.Details; 
import entities.Sale;
import entities.User;
import Constans.Enums.ErrorMessage;
import java.util.List; 
import java.util.Objects;
import java.util.Optional; 
import java.util.stream.Collectors; 
import model.ModelDetail;
import model.ModelSale;
import services.saleService;
import services.userService;
import util.Commons;

/**
 *
 * @author kpalmall
 */ 
public class moduleSale {
    private final saleService saleService;
    
    private final userService userService;
    
    public moduleSale(Double pordesc){ 
        saleService = new saleService();
        userService = new userService();
        this.saleService.newSale(pordesc); 
    }
    
    public void AddProductToCart(int id, int cantidad){
        this.saleService.addLine(id, cantidad); 
    }
    
    public Sale viewDetails(){
        return this.saleService.viewDetails();
    }
    
    public String confirmSale(Integer iduser){
        String message = ErrorMessage.NOTFOUND.getValue();
        
        List<Details> cart = this.viewDetails().getCart();
        
        Optional<User> findUser = userService.findById(iduser);
        if (Commons.collectionNonEmptyOrNull(cart) && findUser.isPresent()) {
            this.viewDetails().setUser(findUser.get());
            message = this.saleService.saveOrder(this.viewDetails()); 
        }
        
        return message;
    }
    
    public List<ModelSale> findAll() {
        return this.saleService.findAll();
    }
    
    public List<ModelSale> findAllByUser(String user) {
        return this.saleService.findAll().stream()
                .filter(sale -> Commons.StringEqualString(sale.getUser(), user))
                .collect(Collectors.toList());
    }
    
    public List<ModelSale> findAllByDate(String date) {
        return this.saleService.findAll().stream()
                .filter(sale -> Commons.StringEqualString(sale.getDate(), date))
                .collect(Collectors.toList());
    }
    
    public List<ModelDetail> findAllDetails(String id) {
        return this.saleService.findAllDetails(id);
    }
}
