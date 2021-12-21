package com.example.lb5;

public class ProductsInfo {
    private int id;
    private String ProductName;
    private String category;
    private String price;

    ProductsInfo(int id, String ProductName, String category, String price)
    {
        this.id = id;
        this.ProductName = ProductName;
        this.category = category;
        this.price = price;
    }

    ProductsInfo()
    {

    }

    public int getId()
    {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName()
    {
        return ProductName;
    }

    public void SetProductName(String ProductName)
    {
        this.ProductName = ProductName;
    }

    public String getCategory()
    {
        return category;
    }

    public void SetCategory(String category)
    {
        this.category = category;
    }

    public String getPrice()
    {
        return price;
    }

    public void SetPrice(String price)
    {
        this.price = price;
    }
}

