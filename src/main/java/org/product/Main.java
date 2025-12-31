package org.product;

import org.product.dao.ProductDao;
import org.product.models.ProductModel;

public class Main {
    public static void main(String[] args) {
        var dao = new ProductDao();
        dao.deleteProductById(5);
        var products = dao.getAllProducts();
        var product = dao.getProductById(3);
        System.out.println(product.name);

        for (var p : products) {
            System.out.println(p.name);
        }

    }
}