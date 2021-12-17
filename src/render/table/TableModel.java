/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.table;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel; 

/**
 *
 * @author kpalmall
 */
public class TableModel {
    
    public void tableNoEditable(JTable table, String headers[]){
        DefaultTableModel model = new DefaultTableModel(null, headers) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };  
        table.setModel(model);
    }
    
}
