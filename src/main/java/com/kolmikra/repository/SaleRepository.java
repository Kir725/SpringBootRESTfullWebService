package com.kolmikra.repository;

import com.kolmikra.model.Book;
import com.kolmikra.model.Sale;
import com.kolmikra.model.Store;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SaleRepository extends CommonRepository<Sale> {

    String queryGetSaleForMonth = "SELECT c.secondname, c.neighborhood, s.sale_date " +
            "FROM sale s JOIN customer c ON s.customer = c.customer_id JOIN store ON store.store_id = s.seller " +
            "WHERE c.neighborhood = store.neighborhood AND EXTRACT(month from s.sale_date) >= :monthNumber " +
            "ORDER BY s.sale_date";

    @Query(value = "SELECT DISTINCT to_char(s.sale_date, 'Month')  FROM sale s", nativeQuery = true)
    List<String> monthsOfSale();

    @Query(value = "SELECT s.customer.secondName, s.seller.name FROM Sale s")
    List<Object[]> getSecNameAndTitleForSale();

    @Query(value = "SELECT s.saleDate,s.customer.secondName,s.customer.discount,s.book.title,s.quantity FROM Sale s")
    List<Object[]> getSacNameDateDiscountBookTitleAndQuantity();

    @Query(value = "SELECT s.id,s.customer.secondName,s.saleDate FROM Sale s WHERE s.saleCost >= :saleCostLevel")
    List<Object[]> getSaleIdCustomerAndDateForSaleCost(@Param("saleCostLevel") double saleCostLevel);

    @Query(value = queryGetSaleForMonth, nativeQuery = true)
    List<Object[]> getSaleInCustomerNeighborhoodForMonth(@Param("monthNumber") int monthNumber);

    @Query(value = "SELECT s.seller FROM Sale s WHERE s.seller.neighborhood <> :neighborhood AND s.customer.discount BETWEEN 10 AND 15")
    List<Store> getStoreFromSaleForNeighborhood(@Param("neighborhood") String neighborhood);

    @Query(value = "SELECT s.book FROM Sale s WHERE s.book.warehouseArea = s.seller.neighborhood AND s.book.quantity > :quantity")
    List<Book> getBookFromSaleForQuantity(@Param("quantity") int quantity);
}
