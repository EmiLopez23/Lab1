package com.tradepal.TradePalApp.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Value {
    @Id
    @GeneratedValue(generator = "valueGen", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String value;

    @ManyToMany
    private List<Category> categoryList;
}
