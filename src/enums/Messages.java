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
public enum Messages {
    EXECUTE_SUCCESS("Datos guardados correctamente."),
    ERROR_CONECTION("No hay conexion."),
    INCORRECT_VALUES("Datos incorrectos."),
    REPETED_VALUES("Datos repetidos."),
    FORBIDENN(" Verifique su correo o su contraseña."),
    RECOVERY_PASSWORD("Escriba su correo, se le enviará una contraseña."),
    RESTORED_PASSWORD("Se reestableció su contraseña."),
    NOTFOUND("Elemento no encontrado."),
    USER_NOTFOUND("Usuario no encontrado.");

    private String value;

    private Messages(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
