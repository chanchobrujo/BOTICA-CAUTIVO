/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.DriverManager;
import java.sql.Connection;

/**
 *
 * @author kpalmall
 */
public class Connectionn {

    public static Connection getConexion() throws Exception {
        Class.forName("org.sqlite.JDBC");

        String src = System.getProperty("user.dir");
        String jdbc = "jdbc:sqlite:";
        
        String osname = System.getProperty("os.name").toUpperCase();
        String url;
        
        if (osname.equals(enums.OSname.LINUX.getName())) url = enums.OSname.LINUX.getSrc();
        else url = enums.OSname.WINDOWS.getSrc();
        
        url = jdbc.concat(src).concat(url); 
        return DriverManager.getConnection(url);
    }
}
