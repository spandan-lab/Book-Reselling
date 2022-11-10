package com.example.demo.model;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Getter
@Setter
public class Price {
//    public Price(String s){
//        super(s);
//    }
    @Autowired
    private BookRepository bookrepository;
    public static float depreciatedPrice(Book book) {
        int i = Integer.parseInt(book.getEdition());
        if (book.getPrice() < 10) {
            return book.getPrice();
        }
        int n = book.getCount() / 10;
        if (i < 2000) {
            return book.getPrice() - ((book.getPrice() * n * 15) / 100);
        }
        return book.getPrice() - ((book.getPrice() * n * 10) / 100);
    }
}
