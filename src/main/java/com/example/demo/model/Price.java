package com.example.demo.model;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
public class Price {
//    public Price(String s){
//        super(s);
//    }
    @Autowired
    private BookRepository bookrepository;
    public static float depreciatedPrice(Book book) {
        if (book.getPrice() < 5) { // if price is less than $5 do not reduce the price
            return book.getPrice();
        }
        int n;
        if(book.getCount()<20) { // if the count of transactions of the book are < 20, then the reducing factor is 1
            n = 1;
        }
        else{
            n = book.getCount() / 10; // else the reducing factor is quotient of the number of transaction of the book
        }
        if (book.getEdition() < 2000) {
            return (book.getPrice() - ((book.getPrice() * n * 12) / 100)); // if the book is more than 22 years old reduce the price by 12 % * reducing factor
        }
        return (book.getPrice() - ((book.getPrice() * n * 10) / 100)); // else reduce the price of the book by 10 % * reducing factor
    }
}
