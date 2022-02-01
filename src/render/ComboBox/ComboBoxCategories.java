/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.ComboBox;

import entities.Category; 
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import modules.modelProduct; 

/**
 *
 * @author kpalmall
 */
public class ComboBoxCategories {
    private modelProduct modulePorduct;  

    public ComboBoxCategories() { 
        modulePorduct = new modelProduct();
    } 
    
    private void getModel(JComboBox jComboBox, String name){
        ((DefaultComboBoxModel) jComboBox.getModel()).addElement(name); 
    }
    
    public void combo_categories(JComboBox jComboBox) { 
        jComboBox.removeAllItems();  
        this.modulePorduct.findAll_Categories()
                .stream()
                .map(Category::getName)
                .forEach(cat -> {
                    this.getModel(jComboBox, cat);
                });
    }
}
