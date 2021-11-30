/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author kpalmall
 */
public class Category {

    private int id;
    private String name;
    private Boolean state;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
        this.state = true;
    }

    public Category(String name, Boolean state) {
        this.name = name;
        this.state = state;
    }

    public Category(int id, String name, Boolean state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

}
