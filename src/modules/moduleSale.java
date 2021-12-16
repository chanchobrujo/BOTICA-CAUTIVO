/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules;

import entities.Sale;
import services.saleService;

/**
 *
 * @author kpalmall
 */
public class moduleSale {
    private final saleService saleService;
    
    public moduleSale(int iduser, Double pordesc){ 
        saleService = new saleService();
        this.saleService.newSale(iduser, pordesc); 
    }
    
    public void AddProductToCart(int id, int cantidad){
        this.saleService.addLine(id, cantidad); 
    }
    
    public Sale viewDetails(){
        return this.saleService.viewDetails();
    }
}
