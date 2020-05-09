package com.kolmikra.service;

import com.kolmikra.model.Book;
import com.kolmikra.repository.BookRepository;
import com.kolmikra.view.TitleAndPriceView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookService extends AbstractService<Book, BookRepository> {

    @Autowired
    BookRepository bookRepository;


    public List<TitleAndPriceView> getBooksWithPrice() {
        return bookRepository.findBy();
    }

    public List<TitleAndPriceView> getBookByNameAndPriceLevel(String namePart, double priceLevel) {
        return bookRepository.getBookByNameAndPriceLevel(namePart, priceLevel);
    }
}
