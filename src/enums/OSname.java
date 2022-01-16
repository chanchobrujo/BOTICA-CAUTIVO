/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enums;
 
import lombok.AllArgsConstructor;
import lombok.Getter; 

import util.Commons;

/**
 *
 * @author chanchobrujo
 */
@Getter 
@AllArgsConstructor
public enum OSname {
    WINDOWS("WINDOWS 10","\\bd\\db.db"),
    LINUX("LINUX","/bd/db.db");
    
    private String name; 
    private String src;  
    
    public static String findUrlByOsName(String osname){ 
        boolean verify = false;
        for (OSname oSname : values()) { 
            verify = Commons.StringEqualString(osname, oSname.getName());  
    private String src;

    private OSname(String name, String src) {
        this.name = name;
        this.src = src;
    } 

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
    
    public static String findUrlByOsName(String osname){ 
        for (OSname oSname : values()) { 
            boolean verify = Commons.StringEqualString(osname, oSname.getName());  
            if (verify) return oSname.getSrc(); 
        }
        return null;
    } 
}
