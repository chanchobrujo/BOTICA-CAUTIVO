/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enums;

import lombok.AllArgsConstructor;
import lombok.Getter; 

/**
 *
 * @author kpalmall
 */
@Getter 
@AllArgsConstructor
public enum AlertMessage {
    FORBIDENN(" Verifique su correo o su contraseña."),
    MESSAGE("Mensaje"),
    RECOVERY_PASSWORD("Escriba su correo, se le enviará una contraseña."),
    RESTORED_PASSWORD("Se reestableció su contraseña.");

    private String value; 
}
