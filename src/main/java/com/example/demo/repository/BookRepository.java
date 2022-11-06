package com.example.demo.repository;

import com.example.demo.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {
    @Query(value = "Select * from Book b where " +
            "(b.Author like %?1% OR b.Title like %?1% OR b.Edition like %?1%) ",
            nativeQuery = true)
    public List<Book> findAllBookByKeyword(String keyword);

    @Query(value = "Select * from Book b where " +
            "(b.isbn = ?1) ",
            nativeQuery = true)
    Optional<Book> findBookByISBN(Long isbn);


}
