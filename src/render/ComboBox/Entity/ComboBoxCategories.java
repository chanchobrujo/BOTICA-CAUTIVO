/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.ComboBox.Entity;

import javax.swing.JComboBox;
import modules.ModuleProduct; 
import render.ComboBox.ComboBoxModel;

/**
 *
 * @author kpalmall
 */
public class ComboBoxCategories {
    private ModuleProduct moduleProduct;  
    private ComboBoxModel comboBoxModel;

    public ComboBoxCategories() { 
        moduleProduct = new ModuleProduct();
        comboBoxModel = new ComboBoxModel();
    } 
    
    public void combo_categories(JComboBox jComboBox) { 
        this.comboBoxModel.combo_categories(jComboBox, this.moduleProduct.findAllCategoriesByCombo());
    }
}
