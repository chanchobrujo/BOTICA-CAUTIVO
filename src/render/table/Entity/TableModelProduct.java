/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.table.Entity;

import entities.Product;
import java.util.List;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modules.ModuleProduct;
import render.table.TableModel;
import util.Commons;
import Constans.Headers.HeadersTableSwing;
import java.util.stream.Stream;
import Constans.Constan;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author kpalmall
 */
public class TableModelProduct {
    private TableModel tableModel;
    private ModuleProduct modulePorduct;

    public TableModelProduct() {
        tableModel = new TableModel();
        modulePorduct = new ModuleProduct();
    } 
    
    private void renderTableStock(JTable table, List<Product> array){
        this.tableModel.tableNoEditable(table, HeadersTableSwing.headres_productstock);
        array.stream().map(product -> {
            StringBuilder productn = new StringBuilder();
            Vector row = new Vector();
            productn.append(product.getName())
                    .append(Constan.empty)
                    .append(product.getBrand());
            
            row.add(product.getId());
            row.add(productn.toString());
            row.add(product.getPrice());
            row.add(product.getStock());
            row.add(product.getCategory().getName());
            return row;
        }).forEachOrdered(row -> {
            ((DefaultTableModel) table.getModel()).addRow(row);
        });
        table.getColumnModel().getColumn(0).setMaxWidth(0);
    }
    
    private void renderTable(JTable table, List<Product> array){
        this.tableModel.tableNoEditable(table, HeadersTableSwing.headres_product);
        
        array.stream().map(product -> {
            Vector row = new Vector();
            row.add(product.getId());
            row.add(product.getName());
            row.add(product.getBrand());
            row.add(product.getPrice());
            row.add(product.getStock());
            row.add(product.getCategory().getName());
            row.add(Commons.BooleanToString(product.getState()));
            row.add(product.getDate());
            return row;
        }).forEachOrdered(row -> ((DefaultTableModel) table.getModel()).addRow(row));
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(3).setMaxWidth(50);
        table.getColumnModel().getColumn(4).setMaxWidth(50);
        table.getColumnModel().getColumn(6).setMaxWidth(70);
    }
    
    public void tableProductData(JTable table){ 
        this.renderTable(table, modulePorduct.findAll_Products().stream().filter(p -> p.getState()).collect(toList()));
    }
    
    public void tableProductDataSearch(JTable table, String value){
        this.renderTable(table, modulePorduct.searchProduct(value));
    }
    
    public void findAllByStockMin(JTable table){
        this.renderTableStock(table, modulePorduct.findAllByStockMin());
    }
    
    public void setProductListUpdate(JTable ...tables){
        Stream.of(tables).forEach(this::tableProductData); 
    }
}
