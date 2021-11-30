/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.Statement;

/**
 *
 * @author kpalmall
 */
public class GestorBd {
    
    
    public static String execute(String sql) {
        String m = "Ejecucion realizada correctamente.";
        try {
            Connection cn = Connectionn.getConexion();
            if (cn!=null) {
                Statement st = cn.createStatement();
                st.executeUpdate(sql);
                cn.close();
            } else {
                m = "No hay conexion.";
            }
        } catch (Exception e) {
            m = e.getMessage();
        }
        return m;
    }
    
}
