package com.example.projectretrofit.Model;

public class Products {

    private  String Id;
    private String ProductName;
    private String Category;
    private String SubCategory;
    private int Price;

    public Products(String id, String productName, String category, String subCategory, int price) {
        Id = id;
        ProductName = productName;
        Category = category;
        SubCategory = subCategory;
        Price = price;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getSubCategory() {
        return SubCategory;
    }

    public void setSubCategory(String subCategory) {
        SubCategory = subCategory;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }
}
