/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author kpalmall
 */
public class Commons {
    public static Boolean toBoolean(int value){
        return  value == 1;
    }
    public static Integer toInteger(Boolean value){
        return  value ? 1 : 0;
    }
    public static String toString(Boolean value){
        return  value ? enums.States.ACTIVO.getValue() 
                : enums.States.INACTIVO.getValue();
    }
    public static Boolean toBoolean_String(String value){
        return  value.endsWith(enums.States.ACTIVO.getValue());
    }
    public static Boolean isEmpty(String value){
        return  value.length() == 0 || Boolean.valueOf(value);
    }
}
