/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entities.Category;
import entities.Customer;
import entities.Product;
import entities.Rol;
import entities.User;
import model.ModelProductsTop;
import model.ModelSale;

import Constans.Constan;

/**
 *
 * @author kpalmall
 */
public class Mapper {

    public static Customer mapperCustomer(Object object) {
        Object[] row = (Object[]) object;
        Integer id = Commons.StringToInteger(row[0].toString());
        Integer dni = Commons.StringToInteger(row[3].toString());

        return Customer.builder()
                .id(id)
                .firtsname(row[1].toString())
                .lastname(row[2].toString())
                .dni(dni)
                .email(row[4].toString())
                .phone(row[5].toString())
                .build();
    }

    public static Rol mapperRol(Object object) {
        Object[] row = (Object[]) object;
        Integer id = Integer.parseInt(row[0].toString());

        return Rol.builder()
                .id(id)
                .name(row[1].toString())
                .build();

    }

    public static Category mapperCategory(Object obj) {
        Object[] category = (Object[]) obj;
        Integer id = Commons.StringToInteger(category[0].toString());
        boolean state = Commons.IntegerToBoolean(Integer.parseInt(category[2].toString()));

        return Category.builder()
                .id(id)
                .name(category[1].toString())
                .state(state)
                .build();
    }

    public static User mapperUser(Object obj) {
        Object[] row = (Object[]) obj;
        Integer id = Integer.parseInt(row[0].toString());
        boolean state = Commons.IntegerToBoolean(Integer.parseInt(row[6].toString()));

        return User.builder()
                .id(id)
                .firtsname(row[1].toString())
                .lastname(row[2].toString())
                .email(row[3].toString())
                .password(row[4].toString())
                .state(state)
                .build();
    }

    public static Product mapperProduct(Object obj) {
        Object[] row = (Object[]) obj;

        Integer id = Integer.parseInt(row[0].toString());
        String name = row[1].toString();
        String brand = row[2].toString();
        Double price = Double.parseDouble(row[3].toString());
        Integer stock = Integer.parseInt(row[4].toString());

        Boolean state = Commons.IntegerToBoolean(Integer.parseInt(row[6].toString()));

        return Product.builder()
                .id(id)
                .name(name)
                .brand(brand)
                .price(price)
                .stock(stock)
                .state(state)
                .build();

    }

    public static ModelSale mapperSale(Object object) {
        Object[] row = (Object[]) object;
        return ModelSale.builder()
                .id(row[0].toString())
                .date(row[1].toString())
                .time(row[2].toString())
                .subtotal(Commons.StringToDouble(row[3].toString()))
                .desc(Commons.StringToDouble(row[4].toString()))
                .total(Commons.StringToDouble(row[5].toString()))
                .build();
    }

    public static ModelProductsTop mapperModelProductsTop(Object object) {
        Object[] row = (Object[]) object;
        Integer idp = Commons.StringToInteger(row[0].toString());
        String name = row[1].toString();
        String brand = row[2].toString();
        Integer count = Commons.StringToInteger(row[3].toString());

        return ModelProductsTop.builder()
                .id_product(idp)
                .name(name)
                .brand(brand)
                .count(count)
                .build();
    }

    public static String mapperProductString(Product mapper) {
        StringBuilder message = new StringBuilder();
        return message.append(mapper.getName())
                .append(Constan.space)
                .append(mapper.getBrand())
                .append(Constan.space)
                .append(String.valueOf(mapper.getStock()))
                .toString();
    }
}
