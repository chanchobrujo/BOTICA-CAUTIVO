/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.ComboBox.Entity;

import javax.swing.JComboBox;
import modules.moduleAuth;
import render.ComboBox.ComboBoxModel;

/**
 *
 * @author kpalmall
 */
public class ComboBoxRoles {
    private moduleAuth moduleAuth;  
    private ComboBoxModel comboBoxModel;

    public ComboBoxRoles() { 
        moduleAuth = new moduleAuth();
        comboBoxModel = new ComboBoxModel();
    } 
    
    public void combo_categories(JComboBox jComboBox) { 
        this.comboBoxModel.combo_categories(jComboBox, this.moduleAuth.findAllRole());
    }
    
}
