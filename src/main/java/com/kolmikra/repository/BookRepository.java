package com.kolmikra.repository;

import com.kolmikra.model.Book;
import com.kolmikra.view.TitleAndPriceView;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends CommonRepository<Book> {

    List<TitleAndPriceView> findBy();

    @Query(value = "SELECT b.title AS title, b.price AS price FROM Book b WHERE b.price > :priceLevel OR b.title Like %:namePart% ORDER BY b.title,b.price DESC ")
    List<TitleAndPriceView> getBookByNameAndPriceLevel(@Param("namePart") String namePart,
                                              @Param("priceLevel") double priceLevel);
}
