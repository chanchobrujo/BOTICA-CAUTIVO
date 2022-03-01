/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.ComboBox;

import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import lombok.NoArgsConstructor;

/**
 *
 * @author kpalmall
 */
@NoArgsConstructor
public class ComboBoxModel { 
    
    private void getModel(JComboBox jComboBox, String name){
        ((DefaultComboBoxModel) jComboBox.getModel()).addElement(name); 
    }
    
    public void combo_categories(JComboBox jComboBox, Set<String> array) { 
        jComboBox.removeAllItems();  
        array.stream().forEach((String role) -> this.getModel(jComboBox, role));
    }
}
