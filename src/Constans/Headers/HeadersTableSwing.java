/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Constans.Headers; 

/**
 *
 * @author kpalmall
 */
public class HeadersTableSwing { 
    public static final String headres_category[] = new String[] {"ID", "NOMBRE", "ESTADO"};
    
    public static final String headres_product[] = 
            new String[] {"ID", "NOMBRE", "MARCA", "PRECIO", "STOCK", "CATEGORIA"
                    , "ESTADO"};
    
    public static final String headres_productstock[] = new String[] {"ID", "PRODUCTO", "PRECIO", "STOCK", "CATEGORIA"};
    
    public static final String headres_cart[] = 
            new String[] {"ID", "PRODUCTO", "PRECIO", "CANTIDAD", "IMPORTE"};
    
    public static final String headres_detail[] = 
            new String[] {"PRODUCTO", "PRECIO", "CANTIDAD", "IMPORTE"};
    
    public static final String headres_sale[] = 
            new String[] {"ID", "USUARIO", "FECHA", "HORA", "DESCUENTO"
                    , "SUBTOTAL", "TOTAL"};
    
    public static final String headres_users[] = 
            new String[] {"ID", "NOMBRES Y APELLIDOS", "EMAIL", "ROL", "ESTADO"};
}
