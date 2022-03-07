/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.table.Entity;

import Constans.Headers.HeadersTableSwing;
import entities.Product;
import entities.User;
import java.util.List;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modules.ModuleProduct;
import modules.ModuleUser;
import render.table.TableModel;
import util.Commons;

/**
 *
 * @author kpalmall
 */
public class TableModelUser {
    private TableModel tableModel;
    private ModuleUser moduleUser;

    public TableModelUser() {
        tableModel = new TableModel();
        moduleUser = new ModuleUser();
    } 
    
    private void renderTable(JTable table, List<User> array){
        this.tableModel.tableNoEditable(table, HeadersTableSwing.headres_users);
        
        array.stream().map(user -> {
            Vector row = new Vector();
            row.add(user.getId());
            row.add(user.getFirtsname().concat(Constans.Constan.empty).concat(user.getLastname()));
            row.add(user.getEmail());
            row.add(user.getRole().getName());
            row.add(Commons.BooleanToString(user.getState()));
            return row;
        }).forEachOrdered(row -> {
            ((DefaultTableModel) table.getModel()).addRow(row);
        });
        
        table.getColumnModel().getColumn(0).setMaxWidth(0);
    }
    
    public void tableUserData(JTable table){ 
        this.renderTable(table, moduleUser.findAllUsers());
    }
    
}
