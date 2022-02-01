/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Product;
import entities.Sale;
import Constans.Constan;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import model.ModelDetail;
import model.ModelSale;
import repository.detailsRepository;
import repository.saleRepository;
import util.Commons;

/**
 *
 * @author chanchobrujo
 */
public class saleService {
    
    private final productService productService; 
    private final Sale sale = new Sale();

    private saleRepository saleRepository;
    private detailsRepository detailsRepository;

    public saleService() { 
        productService = new productService();

        saleRepository = new saleRepository();
        detailsRepository = new detailsRepository();
    }

    private Integer separateStringID(String id) {
        String iduser = Commons.StringSeparate(id, Constan.double_point)[1];
        return Commons.StringToInteger(iduser);

    }

    public void newSale(Double porDes) {
        this.sale.setPordes(porDes);
    }

    public void removeProduct(int id) {
        this.sale.removeProduct(id);
    }

    public void addLine(int id, int cantidad) {
        this.sale.addProduct(productService.findById(id).get(), cantidad);
    }

    public List<Product> findAllProducts() {
        return productService.findAll().stream()
                .filter(Product::getState)
                .collect(Collectors.toList());
    }

    public void clearCart() {
        this.sale.clearCart();
    }

    public Sale viewDetails() {
        return this.sale;
    }

    public String saveOrder(Sale sale) {
        return this.saleRepository.grabarVentasParaUsuarioCliente(sale);
    }

    public List<ModelSale> findAll() {
        return this.saleRepository.findAll();
    }

    public List<ModelSale> findAllSalesByUser(Integer id) {
        return this.saleRepository.findAll().stream()
                .filter(filter -> Objects.equals(this.separateStringID(filter.getUser()), id))
                .collect(Collectors.toList());
    }

    public List<ModelSale> findAllSalesByCustomer(Integer id) {
        return this.saleRepository.findAll().stream()
                .filter(filter -> Objects.equals(this.separateStringID(filter.getCustomer()), id))
                .collect(Collectors.toList());
    } 
    
    public List<ModelDetail> findAllDetails(String id) {
        return this.detailsRepository.findAll(id);
    }

}
