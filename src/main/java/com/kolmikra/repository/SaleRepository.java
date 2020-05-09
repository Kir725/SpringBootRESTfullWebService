package com.kolmikra.repository;

import com.kolmikra.model.Book;
import com.kolmikra.model.Sale;
import com.kolmikra.model.Store;
import com.kolmikra.view.CustomerSecondNameAndStoreTitleView;
import com.kolmikra.view.MonthView;
import com.kolmikra.view.SaleView;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SaleRepository extends CommonRepository<Sale> {

    String queryGetSaleForMonth = "SELECT c.secondname AS secondName, c.neighborhood AS neighborhood," +
            " s.sale_date AS saleDate " +
            "FROM sale s JOIN customer c ON s.customer = c.id JOIN store ON store.id = s.seller " +
            "WHERE c.neighborhood = store.neighborhood AND EXTRACT(month from s.sale_date) >= :monthNumber " +
            "ORDER BY s.sale_date";

    @Query(value = "SELECT DISTINCT to_char(s.sale_date, 'Month') AS month FROM sale s", nativeQuery = true)
    List<MonthView> monthsOfSale();

    @Query(value = "SELECT s.customer.secondName AS secondName, s.seller.name AS name FROM Sale s")
    List<CustomerSecondNameAndStoreTitleView> getSecNameAndTitleForSale();

    @Query(value = "SELECT s.saleDate AS saleDate,s.customer.secondName AS secondName,"+
             "s.customer.discount AS discount,s.book.title AS title,s.quantity AS quantity FROM Sale s")
    List<SaleView> getSacNameDateDiscountBookTitleAndQuantity();

    @Query(value = "SELECT s.id AS id,s.customer.secondName AS secondName," +
            "s.saleDate AS saleDate FROM Sale s WHERE s.saleCost >= :saleCostLevel")
    List<SaleView> getSaleIdCustomerAndDateForSaleCost(@Param("saleCostLevel") double saleCostLevel);

    @Query(value = queryGetSaleForMonth, nativeQuery = true)
    List<SaleView> getSaleInCustomerNeighborhoodForMonth(@Param("monthNumber") int monthNumber);

    @Query(value = "SELECT s.seller FROM Sale s WHERE s.seller.neighborhood <> :neighborhood AND s.customer.discount BETWEEN 10 AND 15")
    List<Store> getStoreFromSaleForNeighborhood(@Param("neighborhood") String neighborhood);

    @Query(value = "SELECT s.book FROM Sale s WHERE s.book.warehouseArea = s.seller.neighborhood AND s.book.quantity > :quantity")
    List<Book> getBookFromSaleForQuantity(@Param("quantity") int quantity);
}
