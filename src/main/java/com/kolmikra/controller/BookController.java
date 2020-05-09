package com.kolmikra.controller;

import com.kolmikra.model.Book;
import com.kolmikra.service.BookService;
import com.kolmikra.view.TitleAndPriceView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest/book")
public class BookController extends AbstractController<Book, BookService> {

    @Autowired
    BookService bookService;

    @GetMapping("/getBooksWithPrice")
    public List<TitleAndPriceView> getBooksWithPrice() {
        return bookService.getBooksWithPrice();
    }

    @GetMapping("/getBookByNamePartAndPriceLevel")
    public ResponseEntity<List<TitleAndPriceView>> getBookByNameAndPriceLevel(@RequestParam String namePart, double priceLevel) {
        List<TitleAndPriceView> result = bookService.getBookByNameAndPriceLevel(namePart, priceLevel);
        if (!result.isEmpty()) {
            return ResponseEntity.ok(result);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
