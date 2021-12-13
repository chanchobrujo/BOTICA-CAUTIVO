/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author kpalmall
 */
public class Details { 
    private Integer id;
    private String id_sale;
    private Product product;
    private Integer quantity;

    public Details(Integer id, String id_sale, Product product, Integer quantity) {
        this.id = id;
        this.id_sale = id_sale;
        this.product = product;
        this.quantity = quantity;
    }
    
    public double getImport(){
        return product.getPrice() * this.quantity;
    } 

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getId_sale() {
        return id_sale;
    }

    public void setId_sale(String id_sale) {
        this.id_sale = id_sale;
    }

    public Product getId_product() {
        return product;
    }

    public void setId_product(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
}
