package com.kolmikra.controller;

import com.kolmikra.model.Book;
import com.kolmikra.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/rest/book")
public class BookController extends AbstractController<Book, BookService> {

    @Autowired
    BookService bookService;

    @GetMapping("/getBooksWithPrice")
    public Map<String, Double> getBooksWithPrice() {
        return bookService.getBooksWithPrice();
    }

    @GetMapping("/getBookByNamePartAndPriceLevel")
    public ResponseEntity<Map<String, Double>> getBookByNameAndPriceLevel(@RequestParam String namePart, double priceLevel) {
        Map<String, Double> result = bookService.getBookByNameAndPriceLevel(namePart, priceLevel);
        if (!result.isEmpty()) {
            return ResponseEntity.ok(result);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public Class<Book> getEType() {
        return Book.class;
    }
}
