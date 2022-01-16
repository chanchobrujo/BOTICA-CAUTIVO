/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.alerts;

import enums.AlertMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author chanchobrujo
 */
public class AlertSuccessMessage {

    public static void alertSetMessage(String value){ 
        JOptionPane.showMessageDialog(null, AlertMessage.MESSAGE.getValue(), 
                value, JOptionPane.OK_OPTION); 
    }
    
}
