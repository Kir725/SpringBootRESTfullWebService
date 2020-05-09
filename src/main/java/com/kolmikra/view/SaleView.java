package com.kolmikra.view;

import java.sql.Date;

public interface SaleView {
    Integer getId();
    Date getSaleDate();
    String getSecondName();
    Double getDiscount();
    String getTitle();
    Double getQuantity();
    String getNeighborhood();
}
