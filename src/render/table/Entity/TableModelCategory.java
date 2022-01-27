/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.table.Entity;

import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modules.modulePorduct;
import render.table.TableModel;
import util.Commons;
import Constans.Headers.HeadersTableSwing;

/**
 *
 * @author kpalmall
 */
public class TableModelCategory {
    private TableModel tableModel;
    private modulePorduct modulePorduct;

    public TableModelCategory() {
        tableModel = new TableModel(); 
        modulePorduct = new modulePorduct();
    } 
    
    private void renderTable(JTable table){
        this.tableModel.tableNoEditable(table, HeadersTableSwing.headres_category); 
        table.removeAll(); 
        
        modulePorduct.findAll_Categories().stream().map(category -> {
            Vector row = new Vector();
            row.add( category.getId() );
            row.add( category.getName() );
            row.add( Commons.BooleanToString(category.getState()) );
            return row;            
        }).forEachOrdered(row -> {
            ((DefaultTableModel) table.getModel()).addRow(row);
        }); 
    }
    
    public void tableCategroyData(JTable table){ 
        this.renderTable(table);
    }
    
}
