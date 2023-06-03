package com.qa.opencart.pojo;

/**
 * Plain Old Java Objects = POJO
 * means Private Variable,
 * Constructor,
 * Getter & Setter and
 * Encapsulation
 */

public class Product {

    private String searchKey;
    private String productName;
    private int productImages;


//Generate =>  CONSTRUCTOR
    public Product(String searchKey, String productName, int productImages) {
        this.searchKey = searchKey;
        this.productName = productName;
        this.productImages = productImages;
    }


//Generate =>  Getter & Setter
    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductImages() {
        return productImages;
    }

    public void setProductImages(int productImages) {
        this.productImages = productImages;
    }

//Generate => toString()
    @Override
    public String toString() { //To give me the exact memory, if this is not added this will give Object Reference
        return "Product{" +
                "searchKey='" + searchKey + '\'' +
                ", productName='" + productName + '\'' +
                ", productImages=" + productImages +
                '}';
    }
}
