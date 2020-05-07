package com.kolmikra.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="customer")
public class Customer extends AbstractEntity{

    @Column(name = "secondname")
    private String secondName;

    private String neighborhood;

    private double discount;
}
