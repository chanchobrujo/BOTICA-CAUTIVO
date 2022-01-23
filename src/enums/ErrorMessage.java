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
public enum ErrorMessage {
    ERROR_CONECTION("No hay conexion."), 
    REPETED_VALUES("Datos repetidos."),
    VOID_VALUES("Datos vacios."),
    NOTFOUND("Elemento no encontrado."), 
    USER_NOTFOUND("Usuario no encontrado."),
    ERROR_TITLE("Error detectado."),
    DATA_VOID("Datos vacios.");

    private String value; 
}
