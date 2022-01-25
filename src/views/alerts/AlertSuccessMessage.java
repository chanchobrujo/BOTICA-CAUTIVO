/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.alerts;

import enums.AlertMessage;
import enums.Constans;
import java.util.List;
import javax.swing.JOptionPane;
import model.ModelDetail; 

/**
 *
 * @author chanchobrujo
 */
public class AlertSuccessMessage {

    public static void alertSetMessage(String value){
        JOptionPane.showMessageDialog(null, value, 
                AlertMessage.MESSAGE.getValue(), JOptionPane.OK_OPTION); 
    }

    public static void alertSetCollections(List<ModelDetail> collection){
        String details = Constans.empty;
        Double total = collection.stream()
                .mapToDouble(ModelDetail::get_import)
                .sum();
        
        for (ModelDetail modelDetail : collection) {
            details += "Producto: "
                    .concat("\n")
                    .concat(" -Nombre: ")
                    .concat(modelDetail.getProduct())
                    .concat("\n")
                    .concat(" -Precio: ")
                    .concat(modelDetail.getProduct_price()+"\n")
                    .concat(" -Cantidad: ")
                    .concat(modelDetail.getQuantity()+"\n")
                    .concat(" -Importe: ")
                    .concat(modelDetail.get_import()+"\n")
                    .concat("\n");
        }
        details = details
                .concat("Total: ")
                .concat(total + Constans.empty);
        
        JOptionPane.showMessageDialog(null, details, 
                AlertMessage.MESSAGE.getValue(), JOptionPane.OK_OPTION); 
    }
    
}
