/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import enums.Constans;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author kpalmall
 */
public class Commons {
    /**
     * Devuelve el valor booleano dependiendo si el dato es 1/0.
     * @param value
     * @return Boolean
     */
    public static Boolean IntegerToBoolean(int value){
        return  value == 1;
    }
    
    /**
     * Convierte un valor 'int' y lo convierte en String.
     * @param id
     * @return String
     */
    public static String IntegerToString(int id){
        return  id + Constans.empty;
    }
    
    /**
     * Convierte un String 'ACTIVO' en un valor booleano.
     * @param value
     * @return Boolean
     */
    public static Boolean StringStateToBoolean(String value){
        return  value.endsWith(enums.State.ACTIVO.getValue());
    }
    
    /**
     * Convierte un booleano en un valor 'int' 1/0.
     * @param value
     * @return Integer
     */
    public static Integer BooleanToInteger(Boolean value){
        return  value ? 1 : 0;
    }
    
    /**
     * Convierte un booleano en un 'String' ACTIVO/INACTIVO.
     * @param value
     * @return String
     */
    public static String BooleanToString(Boolean value){
        return  value ? enums.State.ACTIVO.getValue() 
                : enums.State.INACTIVO.getValue();
    } 
    
    /**
     * Separa una cadena string dependiento del caracter separador.
     * @param value
     * @param constant
     * @return String
     */
    public static String[] StringSeparate(String value, String constant){ 
        return value.split(constant);
    } 
    
    /**
     * Convierte un valor String en un valor 'double', 
     * si hay algun error devuelve -1.0
     * @param value
     * @return Double
     */
    public static Double StringToDouble(String value){
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) { 
            return -1.0;
        }
    }
    
    /**
     * Convierte un valor String en un valor 'Integer', 
     * si hay algun error devuelve -1
     * @param value
     * @return Integer
     */
    public static Integer StringToInteger(String value){
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) { 
            return -1;
        }
    }
    
    /**
     * Recibe un array de Strings, para verificar 
     * si alguno está vacio o es nulo.
     * @param value
     * @return Boolean
     */
    public static Boolean StringsIsEmpty(String ...value){
        for (String string : value) 
            return Objects.equals(string.length(), 0)
                || Objects.isNull(string) 
                || string.equals(Constans.empty)
                || string.equals(Constans.space);
        return false;
    }
    
    /**
     * Recibe un array de Doubles, para verificar 
     * si alguno es 0 o es nulo.
     * @param value
     * @return Boolean
     */
    public static Boolean DoublesIsEmpty(Double ...value){
        for (Double val : value) return val <= 0 
                || Objects.isNull(val);
        return false;
    }
    
    
    /**
     * Recibe un array de Integers, para verificar 
     * si alguno es 0 o es nulo.
     * @param value
     * @return Boolean
     */
    public static Boolean IntegerIsEmpty(Integer ...value){
        for (Integer val : value) return val <= 0 
                || Objects.isNull(val);
        return false;
    }
    
    /**
     * Recibe 2 Strings y verifica si tienen algun coincidencia.
     * @param valueA
     * @param valueB
     * @return Boolean
     */
    public static Boolean StringEqualString(String valueA, String valueB){
        valueA = valueA.toUpperCase();
        valueB = valueB.toUpperCase();
        
        return valueA.equals(valueB) || valueA.contains(valueB);
    } 
    
    /**
     * Recibe un parámetro integer y devuelve un string concatenado con 0.
     * @param value
     * @return String
     */
    private static String completedZero(Integer value){
        String x = value.toString();
        return value < 10 ? Constans.zero.concat(x) : x;
    }
    
    /**
     * Genera un ID para las ventas.
     * @return String
     */
    public static String generatedIdNumber(){
        Date date = new Date();  
        
        String year =  completedZero(date.getYear());
        String month = completedZero(date.getMonth()+1);
        String day = completedZero(date.getDate()) ;
        
        String hour = completedZero(date.getHours());
        String minute = completedZero(date.getMinutes());
        String second = completedZero(date.getSeconds()); 
        
        return year.concat(month)
                .concat(day)
                .concat(hour)
                .concat(minute)
                .concat(second);
    }
    
    public static String[] separateDate(Date date){
        SimpleDateFormat formatDate = new SimpleDateFormat(Constans.format_date); 
        SimpleDateFormat formatTime = new SimpleDateFormat(Constans.format_time); 
        
        String date_ = formatDate.format(date);
        String time_ = formatTime.format(date);
        
        String[] datetime = {date_, time_};
        return datetime; 
    } 
    
    /**
     * Devuelve la fecha en String.
     * @return String
     */
    public static String generatedDateNow(){
        SimpleDateFormat format = new SimpleDateFormat(Constans.format_date); 
        return format.format(new Date()); 
    } 
    
    /**
     * Devuelve la hora en String.
     * @return String
     */
    public static String generatedTimeNow(){
        SimpleDateFormat format = new SimpleDateFormat(Constans.format_time); 
        return format.format(new Date()); 
    }   
    
    /**
     * Recibe una colección y verifica si es nula o está vacia.
     * @param list
     * @return boolean
     */
    public static boolean collectionEmptyOrNull(List<?> list){
        return Objects.isNull(list) || list.isEmpty();
    }
    
    /**
     * Recibe una colección y verifica si NO es nula o está vacia.
     * @param list
     * @return boolean
     */
    public static boolean collectionNonEmptyOrNull(List<?> list){
        return !collectionEmptyOrNull(list);
    }
    
    /**
     * Recibe un objeto date y lo separa para extraer la fecha.
     * @param date
     * @return String
     */
    public static String dateValue(Date date){ 
        boolean verify = Objects.nonNull(date);
        return verify ? separateDate(date)[0] : Constans.empty; 
    }
    
    /**
     * Recibe un objeto date y lo separa para extraer la hora.
     * @param date
     * @return String
     */
    public static String timeValue(Date date){ 
        boolean verify = Objects.nonNull(date);
        return verify ? separateDate(date)[1] : Constans.empty; 
    }
    
}
