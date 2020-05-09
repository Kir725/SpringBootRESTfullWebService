package com.kolmikra.service;

import com.kolmikra.model.Customer;
import com.kolmikra.repository.CustomerRepository;
import com.kolmikra.view.NeighborhoodView;
import com.kolmikra.view.SecondNameAndDiscountView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CustomerService extends AbstractService<Customer, CustomerRepository> {

    @Autowired
    private CustomerRepository customerRepository;

    public List<NeighborhoodView> getCustomersNeighborhood() {
        return customerRepository.findDistinctBy();
    }

    public List<SecondNameAndDiscountView> getCustomerSecNameAndPriceByNeighborhood(String neighborhood) {
        return customerRepository.findByNeighborhood(neighborhood);
    }
}
