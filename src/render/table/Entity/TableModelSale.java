/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.table.Entity;

import enums.Constans;
import java.util.List;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.ModelSale;  
import modules.moduleSale;
import render.table.TableModel;
import util.Commons;
import util.Headers;

/**
 *
 * @author kpalmall
 */
public class TableModelSale {
    private TableModel tableModel;
    private moduleSale moduleSale;

    public TableModelSale() {
        tableModel = new TableModel(); 
        moduleSale = new moduleSale(0.0);
    } 
    
    private void renderTable(JTable table, List<ModelSale> array){
        this.tableModel.tableNoEditable(table, Headers.headres_sale); 
        table.removeAll(); 

        array.stream().map(sale -> {
            Vector row = new Vector(); 
            
            row.add(Commons.StringSeparate(sale.getUser(), Constans.double_point)[0]); 
            row.add(Commons.StringSeparate(sale.getCustomer(), Constans.double_point)[0]); 
            
            row.add(sale.getDate()); 
            row.add(sale.getTime()); 
            
            row.add(sale.getDesc()); 
            row.add(sale.getSubtotal()); 
            row.add(sale.getTotal()); 
            return row;
        }).forEachOrdered(row -> {
            ((DefaultTableModel) table.getModel()).addRow(row);
        });
        
    }
    
    public void tableSaleData(JTable table){ 
        this.renderTable(table, this.moduleSale.findAll());
    }
    
}
