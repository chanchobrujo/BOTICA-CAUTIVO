/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.table.Entity;

import Constans.Constan;
import java.util.List;
import java.util.Vector; 
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.ModelSale;  
import modules.moduleSale;
import render.table.TableModel;
import util.Commons;
import Constans.Headers.HeadersTableSwing;

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
        this.tableModel.tableNoEditable(table, HeadersTableSwing.headres_sale); 
        table.removeAll(); 

        array.stream().map((ModelSale sale) -> {
            Vector row = new Vector(); 
            row.add(sale.getId());
            
            row.add(Commons.StringSeparate(sale.getUser(), Constan.double_point)[0]);
            row.add(sale.getDate());
            row.add(sale.getTime());
            
            row.add(sale.getDesc());
            row.add(sale.getSubtotal());
            row.add(sale.getTotal());
            return row;
        }).forEachOrdered(row -> {
            ((DefaultTableModel) table.getModel()).addRow(row);
        });
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        
        table.getColumnModel().getColumn(1).setMaxWidth(200);
        table.getColumnModel().getColumn(2).setMaxWidth(90);
        table.getColumnModel().getColumn(3).setMaxWidth(90);
        table.getColumnModel().getColumn(4).setMaxWidth(90); 
        table.getColumnModel().getColumn(5).setMaxWidth(90); 
        table.getColumnModel().getColumn(6).setMaxWidth(70); 
        
    }
    
    public void tableSaleData(JTable table){ 
        this.renderTable(table, this.moduleSale.findAll());
    }
    
    public void tableSaleByDateData(JTable table, String date){ 
        this.renderTable(table, this.moduleSale.findAllByDate(date));
    }
    
    public void tableSaleByUserData(JTable table, String user){ 
        this.renderTable(table, this.moduleSale.findAllByUser(user));
    }
    
}
