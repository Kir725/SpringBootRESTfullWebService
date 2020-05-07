package com.kolmikra.service;

import com.kolmikra.exception.NoSuchItemException;
import com.kolmikra.model.Book;
import com.kolmikra.model.Customer;
import com.kolmikra.model.Sale;
import com.kolmikra.model.Store;
import com.kolmikra.repository.CustomerRepository;
import com.kolmikra.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SaleService extends AbstractService<Sale, SaleRepository> {

    @Autowired
    private SaleRepository saleRepository;

    public List<String> monthsOfSale() {
        return saleRepository.monthsOfSale();
    }

    public List<Object[]> getSecNameAndTitleForSale() {
        return saleRepository.getSecNameAndTitleForSale();
    }

    public List<Object[]> getSacNameDateDiscountBookTitleAndQuantity() {
        return saleRepository.getSacNameDateDiscountBookTitleAndQuantity();
    }

    public List<Object[]> getSaleIdCustomerAndDateForSaleCost(double saleCostLevel) {
        return saleRepository.getSaleIdCustomerAndDateForSaleCost(saleCostLevel);
    }

    public List<Object[]> getSaleInCustomerNeighborhoodForMonth(int monthNumber) {
        return saleRepository.getSaleInCustomerNeighborhoodForMonth(monthNumber);
    }

    public List<Store> getStoreFromSaleForNeighborhood(String neighborhood) {
        return saleRepository.getStoreFromSaleForNeighborhood(neighborhood);
    }

    public List<Book> getBookFromSaleForQuantity(int quantity){
        return saleRepository.getBookFromSaleForQuantity(quantity);
    }
}
