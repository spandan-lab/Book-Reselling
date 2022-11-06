package com.example.demo.controller;


import com.example.demo.entity.Book;
import com.example.demo.model.SellNewBookRequest;
import com.example.demo.service.BookService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/user/{userId}/book-sell-new")
    public ResponseEntity<String> saveBook(
            @RequestBody @Valid SellNewBookRequest sellNewBookRequest,
            @PathVariable Long userId) {
        return bookService.saveBook(sellNewBookRequest, userId);
    }

    @GetMapping("/book/all")
    public List<Book> getBooks() {
        return bookService.fetchBooks();
    }

    @GetMapping("/user/{userId}/book-buy/{bookId}")
    public ResponseEntity<String> BuyBookById(
            @PathVariable Long bookId, @PathVariable Long userId) {
        return bookService.buyBookById(bookId, userId);
    }


    @GetMapping("/user/{userId}/book-sell/{id}")
    public ResponseEntity<String> SellBookById(
            @PathVariable("id") Long bookId, @PathVariable Long userId) {

        return bookService.SellBookById(bookId, userId);
    }

    //  Once the user searches the books using the keyword, which might match the title or author or edition the user can buy the book using the id displayed from this output
    @ApiOperation(value = "Get Book by keyword")
    @GetMapping("/books-keyword")
    public List<Book> findAllBookByKeyword(@RequestParam String keyword) {
        return bookService.findAllBookByKeyword(keyword);
    }

    @GetMapping("/books-isbn")
    public Optional<Book> findBookByISBN(@RequestParam String isbn) {
        return bookService.findBookByISBN(isbn);
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<String> updateBook(
            @RequestBody Book book, @PathVariable("id") Long bookId) {
        return bookService.updateBook(book, bookId);
    }

    @DeleteMapping("/book/{id}")
    public String deleteBook(
            @PathVariable("id") Long bookId) {
        bookService.deleteBook(bookId);
        return "Deleted";
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected Map<String,String> handleValidationExceptions(MethodArgumentNotValidException methodArgumentNotValidException) {
        Map<String, String> errors = new HashMap<>();
        methodArgumentNotValidException
                .getBindingResult().getAllErrors().forEach(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorName = error.getDefaultMessage();
                    errors.put(fieldName, errorName);
                });
        return errors;
    }
}
