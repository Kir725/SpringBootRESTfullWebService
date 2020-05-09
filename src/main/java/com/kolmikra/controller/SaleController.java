package com.kolmikra.controller;

import com.kolmikra.model.Book;
import com.kolmikra.model.Sale;
import com.kolmikra.model.Store;
import com.kolmikra.service.SaleService;
import com.kolmikra.view.CustomerSecondNameAndStoreTitleView;
import com.kolmikra.view.MonthView;
import com.kolmikra.view.SaleView;
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
    public List<MonthView> getMonthsOfSale(){
        return saleService.monthsOfSale();
    }

    @GetMapping("/getSecNameAndShopTitle")
    public ResponseEntity<List<CustomerSecondNameAndStoreTitleView>> getSecNameAndShopTitle(){
        List<CustomerSecondNameAndStoreTitleView> result = saleService.getSecNameAndTitleForSale();
        if(!result.isEmpty()){
            return ResponseEntity.ok(result);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getSacNameDateDiscountBookTitleAndQuantity")
    public ResponseEntity<List<SaleView>> getSacNameDateDiscountBookTitleAndQuantity(){
        List<SaleView> result = saleService.getSacNameDateDiscountBookTitleAndQuantity();
        if(!result.isEmpty()){
            return ResponseEntity.ok(result);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getSaleIdCustomerAndDateForSaleCost")
    public ResponseEntity<List<SaleView>> getSaleIdCustomerAndDateForSaleCost(@RequestParam double saleCostLevel){
        List<SaleView> result = saleService.getSaleIdCustomerAndDateForSaleCost(saleCostLevel);
        if(!result.isEmpty()){
            return ResponseEntity.ok(result);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getSaleInCustomerNeighborhoodForMonth")
    public ResponseEntity<List<SaleView>> getSaleInCustomerNeighborhoodForMonth(@RequestParam int monthNumber) {
        List<SaleView> result = saleService.getSaleInCustomerNeighborhoodForMonth(monthNumber);
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

}
