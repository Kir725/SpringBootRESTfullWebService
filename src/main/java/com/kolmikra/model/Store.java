package com.kolmikra.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "store")
public class Store extends AbstractEntity {

    private String name;

    private String neighborhood;

    private double commission;
}
