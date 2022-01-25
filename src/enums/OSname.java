/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enums;
 
import java.util.Optional;
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
    WINDOWS("WINDOWS 10", "\\bd\\db.db", "C:\\"),
    LINUX("LINUX", "/bd/db.db", "");
    
    private String name; 
    private String src;   
    private String srcByPdf;   
        
    public static Optional<OSname> findUrlByOsName(String osname){ 
        for (OSname oSname : values()) { 
            boolean verify = Commons.StringEqualString(osname, oSname.getName());  
            if (verify) return Optional.of(oSname); 
        }
        return Optional.empty();
    } 
}
