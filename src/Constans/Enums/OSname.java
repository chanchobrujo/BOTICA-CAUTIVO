/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Constans.Enums;
 
import java.util.Optional;
import java.util.stream.Stream;
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
    WINDOWS("WINDOWS 10", "\\bd\\db.db", "C:\\Docs\\"),
    LINUX("LINUX", "/bd/db.db", "");
    
    private String name; 
    private String src;   
    private String srcByPdf;   
        
    public static Optional<OSname> findUrlByOsName(String osname){ 
        return Stream.of(values())
                .filter(value ->  Commons.StringEqualString(osname, value.getName()))
                .findFirst();
    } 
}
