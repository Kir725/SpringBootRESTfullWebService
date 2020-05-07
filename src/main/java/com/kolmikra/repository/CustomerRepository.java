package com.kolmikra.repository;

import com.kolmikra.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends CommonRepository<Customer> {


    @Query(value = "SELECT DISTINCT c.neighborhood FROM Customer c")
    List<String> getCustomersNeighborhood();

    @Query(value = "SELECT c.secondName, c.discount FROM Customer c WHERE c.neighborhood = :neighborhood")
    List<Object[]> getCustomerSecNameAndPriceByNeighborhood(@Param("neighborhood") String neighborhood);

}
