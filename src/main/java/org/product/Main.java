package org.product;

import org.product.dao.ProductDao;

public class Main {
    public static void main(String[] args) {
        var dao = new ProductDao();
        var products = dao.getProducts();
        var product = dao.getProduct(3);
        System.out.println(product.name);

        for (var p : products) {
            System.out.println(p.name);
        }

    }
}