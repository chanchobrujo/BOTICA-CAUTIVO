/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entities.Sale;
import Constans.Enums.AlertMessage;
import Constans.Constan;
import entities.Customer;
import entities.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import model.ModelProductsTop;
import model.ModelSale;
import util.Commons;
import util.GestorBd;
import util.Mapper;

/**
 *
 * @author chanchobrujo
 */
public class saleRepository {
    private static final String SCRIPT_SELECT = "SELECT * FROM sale";
    
    private static String FILTER_DATE(String dateS, String dateE){
        boolean verify = !Commons.StringsIsEmpty(dateE, dateS);
        return  verify ? "WHERE datetime(s.date) >= datetime('"+dateS+"') AND datetime(s.date) <= datetime('"+dateE+"') " 
                : Constan.empty;
    }
    
    private static String SCRIPT_REPORT_PRODUCTS_TOP(String top, String dateS, String dateE){
        return "SELECT p.id as 'id_product', p.name, p.brand, COUNT(*) as 'count' FROM sale s "
            + "INNER JOIN detail_sale_product d on s.id = d.id_sale "
            + "INNER JOIN product p on d.id_product = p.id "
            + FILTER_DATE(dateS, dateE)
            + "GROUP BY p.id LIMIT "+ top;
    }

    private customerRepository customerRepository;
    private detailsRepository detailsRepository;
    private userRepository userRepository;

    public saleRepository() {
        userRepository = new userRepository();
        detailsRepository = new detailsRepository();
        customerRepository = new customerRepository();
    }
    
    private String builderName(String first, String last, String id){
        return first
                .concat(Constan.space)
                .concat(last)
                .concat(Constan.double_point)
                .concat(id) ;
    }

    private String UserNameById(String id) {
        User user = this.userRepository.findById(Integer.parseInt(id)); 
        
        return Objects.nonNull(user) 
                ? this.builderName(user.getFirtsname(), user.getLastname(), id)
                : Constan.empty;
    }

    private String CustomerNameById(String id) {
        Customer customer = this.customerRepository.findById(Integer.parseInt(id));  
        
        return Objects.nonNull(customer) 
                ? this.builderName(customer.getFirtsname(), customer.getLastname(), id)
                : Constan.empty;
    }

    public String grabarVentasParaUsuarioCliente(Sale sale) {
        String cus = Objects.isNull(sale.getCustomer()) ? Constan.empty 
                : Constan.space.concat(",") + sale.getCustomer().getId();
        
        String sql = "INSERT INTO sale "
                + "(id,date,time,subtotal,discount,total,state,id_user,id_customer) "
                + "VALUES('" + sale.getId() + "',"
                + " '" + sale.getDate() + "',"
                + " '" + sale.getTime() + "',"
                + " " + sale.getSubtotal() + ","
                + " " + sale.getDesc() + ","
                + " " + sale.getTotal() + ","
                + " " + Commons.BooleanToInteger(sale.getState()) + ","
                + " " + sale.getUser().getId()
                + cus + ")";
        GestorBd.execute(sql);
        this.detailsRepository.grabarDetalles(sale.getCart());

        return AlertMessage.EXECUTE_SUCCESS.getValue();
    }

    public List<ModelSale> findAll() {
        List list = GestorBd.findAll(SCRIPT_SELECT);
        List<ModelSale> findAll = new ArrayList<>();

        if (Commons.collectionNonEmptyOrNull(list))
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

        return findAll;
    }

    public List<ModelProductsTop> productsTop(String top, String dateS, String dateE) {
        List list = GestorBd.findAll(SCRIPT_REPORT_PRODUCTS_TOP(top, dateS, dateE));
        List<ModelProductsTop> findAll = new ArrayList<>(); 
        
        if (Commons.collectionNonEmptyOrNull(list))
            list.stream()
                    .map(mapper -> {
                        findAll.add(Mapper.mapperModelProductsTop(mapper));
                        return mapper;
                    })
                    .collect(Collectors.toList());
        
        return findAll;
    }
}
