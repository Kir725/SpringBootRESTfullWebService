package com.kolmikra.repository;

import com.kolmikra.model.Customer;
import com.kolmikra.view.NeighborhoodView;
import com.kolmikra.view.SecondNameAndDiscountView;

import java.util.List;

public interface CustomerRepository extends CommonRepository<Customer> {

    List<SecondNameAndDiscountView> findByNeighborhood(String neighborhood);

    List<NeighborhoodView> findDistinctBy();
 }
