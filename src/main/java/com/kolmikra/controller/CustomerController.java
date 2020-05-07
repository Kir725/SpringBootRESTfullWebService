package com.kolmikra.controller;

import com.kolmikra.model.Customer;
import com.kolmikra.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest/customer")
public class CustomerController extends AbstractController<Customer, CustomerService> {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/neigborhood")
    public List<String> getNeigborhood() {
        return customerService.getCustomersNeighborhood();
    }

    @GetMapping("/getSecNameAndPriceByNeighborhood")
    public ResponseEntity<Map<String, Double>> getSecNameAndPriceByNeighborhood(@RequestParam String neighborhood) {
        Map<String, Double> result = customerService.getCustomerSecNameAndPriceByNeighborhood(neighborhood);
        if (!result.isEmpty()) {
            return ResponseEntity.ok(result);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public Class<Customer> getEType() {
        return Customer.class;
    }

}
