/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enums;

/**
 *
 * @author kpalmall
 */
public enum ErrorMessage {
    EXECUTE_SUCCESS("Datos guardados correctamente."),
    ERROR_CONECTION("No hay conexion."),
    INCORRECT_VALUES("Datos incorrectos."),
    REPETED_VALUES("Datos repetidos."),
    NOTFOUND("Elemento no encontrado."), 
    USER_NOTFOUND("Usuario no encontrado.");

    private String value;

    private ErrorMessage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
