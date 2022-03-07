/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import Constans.Constan;
import Constans.Enums.State;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

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
        return  id + Constan.empty;
    }
    
    /**
     * Convierte un String 'ACTIVO' en un valor booleano.
     * @param value
     * @return Boolean
     */
    public static Boolean StringStateToBoolean(String value){
        return  value.endsWith(State.ACTIVO.getValue());
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
        return  value ? State.ACTIVO.getValue() 
                : State.INACTIVO.getValue();
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
                || string.equals(Constan.empty)
                || string.equals(Constan.space);
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
        for (Integer val : value) return Objects.isNull(val) 
                || val <= 0;
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
        return value < 10 ? Constan.zero.concat(x) : x;
    }
    
    /**
     * Genera un ID para las ventas.
     * @return String
     */
    public static String generatedIdNumber(){ 
        Calendar c = new GregorianCalendar(); 
        String year =  completedZero(c.get(Calendar.YEAR));
        String month = completedZero(c.get(Calendar.MONTH)+1);
        String day = completedZero(c.get(Calendar.DAY_OF_MONTH)) ;
        
        String hour = completedZero(c.get(Calendar.HOUR_OF_DAY));
        String minute = completedZero(c.get(Calendar.MINUTE));
        String second = completedZero(c.get(Calendar.SECOND)); 
        
        return year.concat(month)
                .concat(day)
                .concat(hour)
                .concat(minute)
                .concat(second);
    }
    
    public static String[] separateDate(Date date){
        SimpleDateFormat formatDate = new SimpleDateFormat(Constan.format_date); 
        SimpleDateFormat formatTime = new SimpleDateFormat(Constan.format_time); 
        
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
        SimpleDateFormat format = new SimpleDateFormat(Constan.format_date); 
        return format.format(new Date()); 
    } 
    
    /**
     * Devuelve la hora en String.
     * @return String
     */
    public static String generatedTimeNow(){
        SimpleDateFormat format = new SimpleDateFormat(Constan.format_time); 
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
        return verify ? separateDate(date)[0] : Constan.empty; 
    }
    
    /**
     * Recibe un objeto date y lo separa para extraer la hora.
     * @param date
     * @return String
     */
    public static String timeValue(Date date){ 
        boolean verify = Objects.nonNull(date);
        return verify ? separateDate(date)[1] : Constan.empty; 
    }
    
    /**
     * Recibe una cadena que separa las propiedades del proudcto.
     * @param value
     * @return String
     */  
    public static String setPropertiesProduct(String value){ 
        String name = Commons.StringSeparate(value, Constan.double_point)[0];
        String brand = Commons.StringSeparate(value, Constan.double_point)[1];
        String category = Commons.StringSeparate(value, Constan.double_point)[2];
        return "    -Nombre: "
                .concat(name)
                .concat(Constan.salt_line)
                .concat("    -Marca: ")
                .concat(brand)
                .concat(Constan.salt_line)
                .concat("    -Categoria: ")
                .concat(category)
                .concat(Constan.salt_line);
    }  
    
    /**
     * Genera una cadena aleatoria de caracteres.
     * @return String
     */
    public static String generatedID() { 
        return (String) UUID.randomUUID().toString().toUpperCase().subSequence(0,8);
    }

    /**
     * Verifica si una cadena es númerica
     * @param chard
     * @return boolean
     */
    private static boolean isNumeric(String chard) {
        try {
            Integer.parseInt(chard);
            return true;
        } catch (NumberFormatException nfe) {
            System.err.println(nfe.getMessage());
            return false;
        }
    }

    /**
     * Verifica si la contraseña es segura.
     *
     * @param password
     * @return boolean
     */
    public static boolean validatePassword(String password) {
        boolean validateLength = password.length() > 6;
        if (validateLength) {
            
            boolean verifyArr = Stream.of(password.split(Constan.empty)).anyMatch(predicate -> predicate.equals("@"));
            boolean verifyAst = Stream.of(password.split(Constan.empty)).anyMatch(predicate -> predicate.equals("*"));
            boolean verifyIsNumeric = Stream.of(password.split(Constan.empty)).anyMatch(predicate -> isNumeric(predicate));

            return (verifyArr || verifyAst) && verifyIsNumeric;
        }
        return validateLength;
    }
}
