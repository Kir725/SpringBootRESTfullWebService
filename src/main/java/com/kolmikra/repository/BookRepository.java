package com.kolmikra.repository;

import com.kolmikra.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends CommonRepository<Book> {

    @Query(value = "SELECT b.title, b.price FROM Book b")
    List<Object[]> getBooksWithPrice();

    @Query(value = "SELECT b.title, b.price FROM Book b WHERE b.price > :priceLevel OR b.title Like %:namePart% ORDER BY b.title,b.price DESC ")
    List<Object[]> getBookByNameAndPriceLevel(@Param("namePart") String namePart,
                                              @Param("priceLevel") double priceLevel);
}
