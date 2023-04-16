package com.tradepal.TradePalApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class CategoryValue {
    @Id
    @GeneratedValue(generator = "valueGen", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String value;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonIgnore
    private Category category;

    public CategoryValue(String value, Category category) {
        this.value = value;
        this.category = category;
    }

    public CategoryValue(){
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }
}
