/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import model.ModelProductsTop;
import repository.saleRepository;

/**
 *
 * @author kpalmall
 */
public class ReportsService {

    private saleRepository saleRepository;

    public ReportsService() {
        saleRepository = new saleRepository();
    }

    public List<ModelProductsTop> productsTop(String top, String dateS, String dateE) {
        return this.saleRepository.productsTop(top, dateS, dateE);
    }
    
}
