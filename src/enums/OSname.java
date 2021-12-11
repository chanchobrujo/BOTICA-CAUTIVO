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
    LINUX("/bd/db.db");
    
    private String src;

    private OSname(String src) {
        this.src = src;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    } 
}
