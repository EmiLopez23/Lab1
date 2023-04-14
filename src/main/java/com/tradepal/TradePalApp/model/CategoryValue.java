package com.tradepal.TradePalApp.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class CategoryValue {
    @Id
    @GeneratedValue(generator = "valueGen", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String value;

    @ManyToMany
    private List<Category> categoryList;

    public CategoryValue(String value, List<Category> categoryList) {
        this.value = value;
        this.categoryList = categoryList;
    }

    public CategoryValue(){
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public Long getId() {
        return id;
    }
}
