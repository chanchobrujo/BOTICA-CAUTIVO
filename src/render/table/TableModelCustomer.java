/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.table;

import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modules.moduleCustomer;  
import util.Headers;

/**
 *
 * @author chanchobrujo
 */
public class TableModelCustomer {
    private TableModel tableModel;
    private moduleCustomer moduleCustomer;

    public TableModelCustomer() {
        this.tableModel = new TableModel();
        this.moduleCustomer = new moduleCustomer();
    }
    
    private void renderTable(JTable table){
        this.tableModel.tableNoEditable(table, Headers.headres_customer); 
        table.removeAll(); 
        
        moduleCustomer.findAll().stream().map(customer -> {
            Vector row = new Vector();
            row.add(customer.getId());
            row.add(customer.getFirtsname());
            row.add(customer.getLastname());
            row.add(customer.getDni());
            row.add(customer.getEmail());
            row.add(customer.getPhone());
            return row;          
        }).forEachOrdered(row -> {
            ((DefaultTableModel) table.getModel()).addRow(row);
        });  
    }
    
    public void tableCustomerData(JTable table){ 
        this.renderTable(table);
    }

    
}
