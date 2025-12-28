package org.product.models;

public class ProductModel {
    public int id;
    public String name;
    public int price;
    public int count;
    public String code;
    public String supplier;

    public ProductModel(int id, String name, int price, int count, String code, String supplier) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.count = count;
        this.code = code;
        this.supplier = supplier;
    }
}
