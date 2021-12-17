/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.table;

import entities.Product;
import java.util.List;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modules.modulePorduct;
import util.Commons;
import util.Headers;

/**
 *
 * @author kpalmall
 */
public class TableModelProduct {
    private modulePorduct modulePorduct;

    public TableModelProduct() {
        modulePorduct = new modulePorduct();
    } 
    
    private void renderTable(JTable table, List<Product> array){
        this.tableNoEditable(table);
        for (Product product : array) {
            Vector row = new Vector();

            row.add(product.getId());
            row.add(product.getName());
            row.add(product.getBrand());
            row.add(product.getPrice());
            row.add(product.getStock());
            row.add(product.getCategory().getName());
            row.add(Commons.BooleanToString(product.getState()));

            ((DefaultTableModel) table.getModel()).addRow(row);
        }
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(3).setMaxWidth(50);
        table.getColumnModel().getColumn(4).setMaxWidth(50);
        table.getColumnModel().getColumn(6).setMaxWidth(70);
    }
    
    private void tableNoEditable(JTable table){
        DefaultTableModel model = new DefaultTableModel(null, Headers.headres_product) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };  
        table.setModel(model);
    }
    
    public void tableProductData(JTable table){ 
        this.renderTable(table, modulePorduct.findAll_Products_Active());
    }
    
    public void tableProductDataSearch(JTable table, String value){
        this.renderTable(table, modulePorduct.searchProduct(value));
    }
}
