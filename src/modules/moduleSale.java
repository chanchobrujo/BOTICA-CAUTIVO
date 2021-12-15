/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules;

import services.saleService;

/**
 *
 * @author kpalmall
 */
public class moduleSale {
    private final saleService saleService = new saleService();
    
    public moduleSale(){ 
        this.saleService.newSale(1, 0.5);
    }
    
    public void AddProductToCart(int id, int cantidad){
        this.saleService.addLine(id, cantidad);
    }
}
