package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.model.SellNewBookRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {

    ResponseEntity<String> saveBook(SellNewBookRequest sellNewBookRequest, Long userId);

//    ResponseEntity<String> saveBook(Book book);

    List<Book> fetchBooks();

    ResponseEntity<String> buyBookById(Long bookId, Long userId);

    ResponseEntity<String> updateBook(Book book, Long bookId);

    void deleteBook(Long bookId);

//      public List<Book> findByBookCodeOrDepartmentName(String deptCode, String departmentName);

    List<Book> findAllBookByKeyword(String keyword);

    ResponseEntity<String> SellBookById(Long bookId, Long userId);


    Optional<Book> findBookByISBN(String isbn);

//    List<Book> findByDepartmentName(String departmentName);

}
