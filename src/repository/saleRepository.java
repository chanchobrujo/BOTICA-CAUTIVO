/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entities.Sale;
import util.Commons;
import util.GestorBd;

/**
 *
 * @author chanchobrujo
 */
public class saleRepository {

    private productRepository productRepository;

    public saleRepository() {
        productRepository = new productRepository();
    }

    public String grabarVentasParaUsuarioCliente(Sale sale) {
        String sql = "INSERT INTO sale "
                + "(id,date,time,subtotal,discount,total,state,id_user,id_customer) "
                + "VALUES('" + sale.getId() + "',"
                + " '" + sale.getDate() + "',"
                + " '" + sale.getTime() + "',"
                + " " + sale.getSubtotal() + ","
                + " " + sale.getDesc() + ","
                + " " + sale.getTotal() + ","
                + " " + Commons.BooleanToInteger(sale.getState()) + ","
                + " " + sale.getUser().getId() + ","
                + " " + sale.getCustomer().getId() + ")";
        return GestorBd.execute(sql);
    }
}
