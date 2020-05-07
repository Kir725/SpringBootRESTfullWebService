package com.kolmikra.service;

import com.kolmikra.model.Customer;
import com.kolmikra.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

@Service
public class CustomerService extends AbstractService<Customer, CustomerRepository> {

    @Autowired
    private CustomerRepository customerRepository;

    public List<String> getCustomersNeighborhood() {
        return customerRepository.getCustomersNeighborhood();
    }

    public Map<String, Double> getCustomerSecNameAndPriceByNeighborhood(String neighborhood) {
        return customerRepository.getCustomerSecNameAndPriceByNeighborhood(neighborhood)
                .stream()
                .collect(Collectors.toMap(
                        object -> ((String) object[0]),
                        object -> ((Number) object[1]).doubleValue()
                ));
    }

}
