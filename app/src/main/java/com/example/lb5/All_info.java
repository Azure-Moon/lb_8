package com.example.lb5;

public class All_info {

    private int id;
    private String corps;
    private String founder;
    private String product;
    private String pcategory;
    private String pprice;

    public All_info(int id, String corps, String founder, String product, String pcategory, String pprice) {
        this.id = id;
        this.corps = corps;
        this.founder = founder;
        this.product = product;
        this.pcategory = pcategory;
        this.pprice = pprice;
    }

    public All_info()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAllCorps() {
        return corps;
    }

    public void setAllCorps(String corps) {
        this.corps = corps;
    }

    public String getAllProducts() {
        return product;
    }

    public void setAllProducts(String product) {
        this.product = product;
    }

    public String getAllPcategory() {return pcategory; }

    public void setAllPcategory(String pcategory) { this.pcategory = pcategory; }

    public String getAllPprice() {return pprice; }

    public void setAllPprice(String pprice) {this.pprice = pprice; }

    public String getAllFounders() {
        return founder;
    }

    public void setAllFounders(String founders) {
        this.founder = founders;
    }
}
