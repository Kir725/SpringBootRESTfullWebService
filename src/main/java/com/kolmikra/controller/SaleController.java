package com.kolmikra.controller;

import com.kolmikra.model.Book;
import com.kolmikra.model.Sale;
import com.kolmikra.model.Store;
import com.kolmikra.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/sale")
public class SaleController extends AbstractController<Sale, SaleService> {

    @Autowired
    SaleService saleService;

    @GetMapping("/monthsOfSale")
    public List<String> getMonthsOfSale(){
        return saleService.monthsOfSale();
    }

    @GetMapping("/getSecNameAndShopTitle")
    public ResponseEntity<List<Object[]>> getSecNameAndShopTitle(){
        List<Object[]> result = saleService.getSecNameAndTitleForSale();
        if(!result.isEmpty()){
            return ResponseEntity.ok(result);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getSacNameDateDiscountBookTitleAndQuantity")
    public ResponseEntity<List<Object[]>> getSacNameDateDiscountBookTitleAndQuantity(){
        List<Object[]> result = saleService.getSacNameDateDiscountBookTitleAndQuantity();
        if(!result.isEmpty()){
            return ResponseEntity.ok(result);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getSaleIdCustomerAndDateForSaleCost")
    public ResponseEntity<List<Object[]>> getSaleIdCustomerAndDateForSaleCost(@RequestParam double saleCostLevel){
        List<Object[]> result = saleService.getSaleIdCustomerAndDateForSaleCost(saleCostLevel);
        if(!result.isEmpty()){
            return ResponseEntity.ok(result);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getSaleInCustomerNeighborhoodForMonth")
    public ResponseEntity<List<Object[]>> getSaleInCustomerNeighborhoodForMonth(@RequestParam int monthNumber) {
        List<Object[]> result = saleService.getSaleInCustomerNeighborhoodForMonth(monthNumber);
        if (!result.isEmpty()) {
            return ResponseEntity.ok(result);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getStoreFromSaleForNeighborhood")
    public ResponseEntity<List<Store>> getStoreFromSaleForNeighborhood(@RequestParam String neighborhood){
        List<Store> result = saleService.getStoreFromSaleForNeighborhood(neighborhood);
        if (!result.isEmpty()) {
            return ResponseEntity.ok(result);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getBookFromSaleForQuantity")
    public ResponseEntity<List<Book>> getBookFromSaleForQuantity(@RequestParam int quantity){
        List<Book> result = saleService.getBookFromSaleForQuantity(quantity);
        if (!result.isEmpty()) {
            return ResponseEntity.ok(result);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public Class<Sale> getEType() {
        return Sale.class;
    }

}
