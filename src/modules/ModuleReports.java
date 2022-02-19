/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules;

import java.util.List;
import model.ModelProductsTop;
import services.ReportsService;

/**
 *
 * @author kpalmall
 */
public class ModuleReports {
    private ReportsService reportsService;
    
    public ModuleReports(){ 
        reportsService = new ReportsService();
    }

    public List<ModelProductsTop> productsTop() {
        return this.reportsService.productsTop();
    }
    
}
