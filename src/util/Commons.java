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
    public static Boolean IntegerToBoolean(int value){
        return  value == 1;
    }
    public static String IntegerToString(int id){
        return  id + Constans.empty;
    }
    public static Boolean StringToBoolean(String value){
        return  value.endsWith(enums.State.ACTIVO.getValue());
    }
    public static Integer BooleanToInteger(Boolean value){
        return  value ? 1 : 0;
    }
    public static String BooleanToString(Boolean value){
        return  value ? enums.State.ACTIVO.getValue() 
                : enums.State.INACTIVO.getValue();
    } 
    public static Double StringToDouble(String value){
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) { 
            return -1.0;
        }
    }
    public static Integer StringToInteger(String value){
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) { 
            return -1;
        }
    }
     
    public static Boolean StringsIsEmpty(String ...value){
        for (String string : value) return string.length() == 0 
                || Objects.isNull(string) 
                || string.equals(Constans.empty)
                || string.equals(Constans.space);
        return false;
    }
    public static Boolean DoublesIsEmpty(Double ...value){
        for (Double val : value) return val <= 0;
        return false;
    }
    public static Boolean IntegerIsEmpty(Integer ...value){
        for (Integer val : value) return val <= 0;
        return false;
    }
    
    public static Boolean StringEqualString(String valueA, String valueB){
        valueA = valueA.toUpperCase();
        valueB = valueB.toUpperCase();
        
        return valueA.equals(valueB) || valueA.contains(valueB);
    } 
    public static String generatedIdNumber(){
        Date date = new Date();  
        
        String year = date.getYear() + "";
        String month = (date.getMonth()+1) + "";
        String day = date.getDate() + "";
        
        String hour = date.getHours() + "";
        String minute = date.getMinutes() + "";
        String second = date.getSeconds() + ""; 
        
        return year.concat(month)
                .concat(day)
                .concat(hour)
                .concat(minute)
                .concat(second);
    }
    public static String generatedDateNow(){
        SimpleDateFormat format = new SimpleDateFormat(Constans.format_date); 
        return format.format(new Date()); 
    } 
    public static String generatedTimeNow(){
        SimpleDateFormat format = new SimpleDateFormat(Constans.format_time); 
        return format.format(new Date()); 
    }   

    public static boolean collectionEmptyOrNull(List<?> list){
        return Objects.isNull(list) || list.isEmpty();
    }
    public static boolean collectionNonEmptyOrNull(List<?> list){
        return !( Objects.isNull(list) || list.isEmpty() );
    }
}
