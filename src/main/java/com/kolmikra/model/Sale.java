package com.kolmikra.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "sale_with_cost")
public class Sale extends AbstractEntity {

    @Column(name = "sale_date")
    private Date saleDate;

    @ManyToOne
    @JoinColumn(name = "seller")
    private Store seller;

    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "book")
    private Book book;

    private double quantity;

    @Column(name = "sale_cost", insertable = false, updatable = false)
    private double saleCost;

}
