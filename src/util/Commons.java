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
    public static Boolean IntegerToBoolean(int value){
        return  value == 1;
    }
    public static Boolean StringToBoolean(String value){
        return  value.endsWith(enums.States.ACTIVO.getValue());
    }
    public static Integer BooleanToInteger(Boolean value){
        return  value ? 1 : 0;
    }
    public static String BooleanToString(Boolean value){
        return  value ? enums.States.ACTIVO.getValue() 
                : enums.States.INACTIVO.getValue();
    } 
    public static Double StringToDouble(String value){
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            System.err.println(e);
            return -1.0;
        }
    }
    public static Integer StringToInteger(String value){
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            System.err.println(e);
            return -1;
        }
    }
     
    public static Boolean StringsIsEmpty(String ...value){
        for (String string : value) return string.length() == 0 
                || Boolean.valueOf(string);
        return false;
    }
    public static Boolean DoublesIsEmpty(Double ...value){
        for (Double val : value) return val <= 0;
        return false;
    }
    
    public static Boolean StringEqualString(String valueA, String valueB){
        valueA = valueA.toUpperCase();
        valueB = valueB.toUpperCase();
        
        return valueA.equals(valueB) || valueA.contains(valueB);
    } 
}
