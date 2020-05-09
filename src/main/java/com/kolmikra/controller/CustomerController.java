package com.kolmikra.controller;

import com.kolmikra.model.Customer;
import com.kolmikra.service.CustomerService;
import com.kolmikra.view.NeighborhoodView;
import com.kolmikra.view.SecondNameAndDiscountView;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/customer")
public class CustomerController extends AbstractController<Customer, CustomerService> {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/neigborhood")
    public List<NeighborhoodView> getNeigborhood() {
        return customerService.getCustomersNeighborhood();
    }

    @GetMapping("/getSecNameAndPriceByNeighborhood")
    public ResponseEntity<List<SecondNameAndDiscountView>> getSecNameAndPriceByNeighborhood(@RequestParam String neighborhood) {
        List<SecondNameAndDiscountView> result = customerService.getCustomerSecNameAndPriceByNeighborhood(neighborhood);
        if (!result.isEmpty()) {
            return ResponseEntity.ok(result);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
