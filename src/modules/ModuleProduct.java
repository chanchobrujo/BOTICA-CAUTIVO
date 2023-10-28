/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modules;

import entities.Category;
import entities.Product;
import Constans.Enums.ErrorMessage;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import services.categoryService;
import services.ProductService;
import util.Commons;

/**
 *
 * @author umbke
 */
public class ModuleProduct {

    private static final String messageError = ErrorMessage.DATA_VOID.getValue();

    private categoryService categoryService;
    private ProductService productService;

    public ModuleProduct() {
        categoryService = new categoryService();
        productService = new ProductService();
    }

    public String saveCategory(int id, String name, Boolean state) {
        boolean verify = Commons.StringsIsEmpty(name);
        return verify ? messageError : categoryService.save(id, name);
    }

    public String changeStateCategory(int id) {
        boolean verify = Commons.IntegerIsEmpty(id);
        return verify ? messageError : categoryService.changeState(id);
    }

    public Set<String> findAllCategoriesByCombo() {
        return this.categoryService.findAll().stream()
                .map(Category::getName)
                .collect(Collectors.toSet());
    }

    public List<Category> findAll_Categories() {
        return categoryService.findAll();
    }

    public List<Category> findByStates_Categories(Boolean state) {
        return categoryService.findAll_States(state);
    }

    public Optional<Category> findByName_Categories(String name) {
        return categoryService.findByName(name);
    }

    public List<Product> findAll_Products() {
        return productService.findAll();
    }

    public List<Product> findAll_Products_Active() {
        return productService.findAll().stream()
                .filter(p -> p.getState())
                .collect(Collectors.toList());
    }

    public String saveProduct(Integer id, String name, String brand,
            Double price, Integer Stock, String category, String date) {
        boolean verify = Commons.StringsIsEmpty(name, brand, category)
                || Commons.IntegerIsEmpty(Stock) || Commons.DoublesIsEmpty(price);
        
        return verify ? messageError : this.productService.save(id, name, brand, price, Stock, category, date);
    }

    public String changeStateProduct(Integer id) {
        boolean verify = Commons.IntegerIsEmpty(id);
        return verify ? messageError : productService.changeState(id);
    }
    
    public String addStock(Integer id, Integer quantity) {
        boolean verify = Commons.IntegerIsEmpty(id, quantity); 
        return verify ? messageError : productService.addStock(id, quantity);
    }

    public List<Product> searchProduct(String value) {
        return productService.searchProduct(value).stream().filter(p -> p.getState()).collect(toList());
    }

    public Optional<Product> findById_Products(Integer id) {
        return productService.findById(id);
    }

    public List<Product> findAllByStockMin() {
        return this.productService.findAllByStockMin();
    }
}
