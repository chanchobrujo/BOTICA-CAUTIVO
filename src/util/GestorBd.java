/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import enums.AlertMessage;
import enums.ErrorMessage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author kpalmall
 */
public class GestorBd {

    public static String execute(String sql) {
        String m = AlertMessage.EXECUTE_SUCCESS.getValue();
        try {
            Connection cn = Connectionn.getConexion();
            if (cn != null) {
                Statement st = cn.createStatement();
                st.executeUpdate(sql);
                cn.close();
            } else {
                m = ErrorMessage.ERROR_CONECTION.getValue();
            }
        } catch (Exception e) {
            m = e.getMessage();
        }
        return m;
    }

    public static Object[] find(String sql) {
        Object[] fila = null;
        List lista = findAll(sql);
        if (lista != null) {
            if (lista.size() > 1) {
                fila = (Object[]) lista.get(1);
            }
        }
        return fila;
    }

    public static List findAll(String sql) {
        List lista = new ArrayList<>();
        try {
            Connection cn = Connectionn.getConexion();
            if (Objects.nonNull(cn)) {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                ResultSetMetaData rm = rs.getMetaData();
                int numCol = rm.getColumnCount();

                while (rs.next()) {
                    Object[] fila = new Object[numCol];
                    for (int i = 0; i < numCol; i++) {
                        fila[i] = rs.getObject(i + 1);
                    }
                    lista.add(fila);
                }
                cn.close();
            } 
        } catch (Exception e) {
            lista = new ArrayList<>();
        }
        return lista;
    }

}
