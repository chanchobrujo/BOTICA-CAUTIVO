/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.table;

import entities.Details;
import entities.Product;
import java.util.List;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modules.modulePorduct;
import util.Headers;

/**
 *
 * @author kpalmall
 */
public class TableModelCart { 
    private TableModel tableModel;

    public TableModelCart() {
        tableModel = new TableModel(); 
    } 
    
    private void renderTable(JTable table, List<Details> array){
        this.tableModel.tableNoEditable(table, Headers.headres_cart); 
        table.removeAll(); 

        array.stream().map(details -> {
            Vector row = new Vector();
            Integer ifprod = details.getProduct().getId();
            String product = details.getProduct().getName() + " " +details.getProduct().getBrand();
            Double price = details.getProduct().getPrice();
            Double impor = details.getImport();
            Integer quantity = details.getQuantity();
            
            row.add( ifprod );
            row.add( product ); 
            row.add( price );
            row.add( quantity );
            row.add( impor );
            
            return row;
        }).forEachOrdered(row -> {
            ((DefaultTableModel) table.getModel()).addRow(row);
        });
        
    }
    
    public void tableCartData(JTable table, List<Details> array){ 
        this.renderTable(table, array);
    }
}
