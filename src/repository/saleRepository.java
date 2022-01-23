/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import com.jgoodies.common.base.Objects;
import entities.Sale;
import enums.AlertMessage;
import enums.Constans;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.ModelSale;
import util.Commons;
import util.GestorBd;
import util.Mapper;

/**
 *
 * @author chanchobrujo
 */
public class saleRepository {

    private customerRepository customerRepository;
    private detailsRepository detailsRepository;
    private userRepository userRepository;

    public saleRepository() {
        userRepository = new userRepository();
        detailsRepository = new detailsRepository();
        customerRepository = new customerRepository();
    }

    private String UserNameById(String id) {
        return this.userRepository.findAll().stream()
                .filter(user -> Objects.equals(user.getId(), Commons.StringToInteger(id)))
                .map(mapper -> {
                    return mapper.getFirtsname()
                            .concat(Constans.space)
                            .concat(mapper.getLastname())
                            .concat(Constans.double_point)
                            .concat(id);
                })
                .findFirst()
                .get();
    }

    private String CustomerNameById(String id) {
        return this.customerRepository.findAll().stream()
                .filter(customer -> Objects.equals(customer.getId(), Commons.StringToInteger(id)))
                .map(mapper -> {
                    return mapper.getFirtsname()
                            .concat(Constans.space)
                            .concat(mapper.getLastname())
                            .concat(Constans.double_point)
                            .concat(id);
                })
                .findFirst()
                .get();
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
        GestorBd.execute(sql);
        this.detailsRepository.grabarDetalles(sale.getCart());

        return AlertMessage.EXECUTE_SUCCESS.getValue();
    }

    public List<ModelSale> findAll() {
        List list = GestorBd.findAll("SELECT * FROM sale;");
        List<ModelSale> findAll = new ArrayList<>();

        if (Commons.collectionNonEmptyOrNull(list)) {
            list.stream()
                    .map(mapper -> {
                        ModelSale modelSale = Mapper.mapperSale(mapper);
                        Object[] row = (Object[]) mapper;

                        modelSale.setUser(this.UserNameById(row[7].toString()));
                        modelSale.setCustomer(this.CustomerNameById(row[8].toString()));

                        findAll.add(modelSale);
                        return mapper;
                    })
                    .collect(Collectors.toList());
        }

        return findAll;
    }
}
