/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enums;

/**
 *
 * @author chanchobrujo
 */
public enum OSname {
    WINDOWS("\\bd\\db.db"),
    LINUX("LINUX","/bd/db.db");
    
    private String name;
    private String src;

    private OSname(String name, String src) {
        this.name = name;
        this.src = src;
    }

    private OSname(String src) { 
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
    
}
