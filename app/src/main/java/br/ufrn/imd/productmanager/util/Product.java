package br.ufrn.imd.productmanager.util;

import java.io.Serializable;

public class Product implements Serializable {

    private String codeProduct;
    private String nameProduct;
    private String descProduct;
    private int quantity;

    public Product(String codeProduct, String nameProduct, String descProduct, int quantity) {
        this.codeProduct = codeProduct;
        this.nameProduct = nameProduct;
        this.descProduct = descProduct;
        this.quantity = quantity;
    }

    public String getCodeProduct() {
        return codeProduct;
    }

    public void setCodeProduct(String codeProduct) {
        this.codeProduct = codeProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getDescProduct() {
        return descProduct;
    }

    public void setDescProduct(String descProduct) {
        this.descProduct = descProduct;
    }

    public int getQuantity() {
        return quantity;
    }
    public String getQuantityString() {
        return Integer.toString(this.quantity);
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
