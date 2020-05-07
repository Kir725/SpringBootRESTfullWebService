package com.kolmikra.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "book")
public class Book extends AbstractEntity{

    private String title;

    private double price;

    @Column(name = "warehouse_area")
    private String warehouseArea;

    private int quantity;
}
