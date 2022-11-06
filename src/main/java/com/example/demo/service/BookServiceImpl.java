package com.example.demo.service;


import com.example.demo.entity.Book;
import com.example.demo.entity.User;
import com.example.demo.model.SellNewBookRequest;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    public static final float PERCENT = 10;
    @Autowired
    private BookRepository bookrepository;
    @Autowired
    private UserRepository userrepository;

    private static float evaluateDepreciatedPrice(Book book) {
        return book.getPrice() - ((book.getPrice() * PERCENT) / 100);
    }

    @Override
    public ResponseEntity<String> saveBook(SellNewBookRequest sellNewBookRequest, Long userId) {
        Optional<User> user = userrepository.findById(userId);
        if (user.isPresent()) {
            Optional<Book> bookByISBN = bookrepository.findBookByISBN(sellNewBookRequest.getIsbn());
            if (bookByISBN.isPresent()) {
                return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Book already present. Please update the details or check credentials");
            } else {
                Book book = buildBookObject(sellNewBookRequest);
                book.setUser(user.get());
                bookrepository.save(book);
                return ResponseEntity.status(HttpStatus.OK).body("Book added successfully. " + book);
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    private Book buildBookObject(SellNewBookRequest sellNewBookRequest) {
        Book book = new Book();
        book.setISBN(sellNewBookRequest.getIsbn());
        book.setTitle(sellNewBookRequest.getTitle());
        book.setEdition(sellNewBookRequest.getEdition());
        book.setAuthor(sellNewBookRequest.getAuthor());
        book.setPrice(sellNewBookRequest.getPrice());
        return book;
    }

    @Override
    public List<Book> fetchBooks() {
        return (List<Book>) bookrepository.findAll();
    }

    @Override
    public ResponseEntity<String> buyBookById(Long bookId, Long userId) {
        if (userrepository.existsById(userId)) {
            if (bookrepository.existsById(bookId)) {
            Book book = bookrepository.findById(bookId).orElse(null);
            if (book.getCount() == 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The book " + book.getTitle() + " is not in stock");
            } else {
                if (book.getUser().getUserId() == userId)
                    return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("The user " + userId + " owns the book. Cannot buy again.");
                book.setCount(0);
                float num = evaluateDepreciatedPrice(book);
                book.setPrice(num);
                book.setUser(userrepository.findById(userId).get());
                bookrepository.save(book);
                return ResponseEntity.status(HttpStatus.OK).body("Thank you for buying the book" + "\n" + "BookId: " + book.getBookId() + "\n" + "Title: " + book.getTitle() + "\n" + "Author: " + book.getAuthor() + "\n" + "Edition: " + book.getEdition() + "\n" + "ISBN: " + book.getISBN() + "\n" + "Price: " + book.getPrice()*1.1 + "\n" + "User: " + book.getUser()+ "\n" + "Count: " + book.getCount() + "\n");
            }
                } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The book " + bookId + " does not exist");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The user " + userId + " does not exist");
        }
    }

    @Override
    public ResponseEntity<String> SellBookById(Long bookId, Long userId) {
        if (userrepository.existsById(userId)) {
            if (bookrepository.existsById(bookId)) {
                Book book = bookrepository.findById(bookId).orElse(null);
                if (book.getCount() == 1) {
                    return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("This book is already sold by you.");
                }
                if (book.getUser().getUserId() == userId) {
                    book.setCount(1);
                    bookrepository.save(book);
                    return ResponseEntity.status(HttpStatus.OK).body("Book sold successfully." + "\n" + "BookId: " + book.getBookId() + "\n" + "Title: " + book.getTitle() + "\n" + "Author: " + book.getAuthor() + "\n" + "Edition: " + book.getEdition() + "\n" + "ISBN: " + book.getISBN() + "\n" + "Price: " + book.getPrice() + "\n" + "User: " + book.getUser()+ "\n" + "Count: " + book.getCount() + "\n");
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot sell. Not  owner of the book.");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }


    @Override
    public Optional<Book> findBookByISBN(String isbn) {
        return Optional.empty();
    }

    @Override
    public ResponseEntity<String> updateBook(Book book, Long bookId) {
        Book bookdb = bookrepository.findById(bookId).orElse(null);
        if(bookdb.getISBN() != null) {

            if (Objects.nonNull(book.getAuthor()) && !"".equalsIgnoreCase(book.getAuthor()))
                bookdb.setAuthor(book.getAuthor());

            if (Objects.nonNull(book.getISBN()))
                bookdb.setISBN(book.getISBN());

            if (Objects.nonNull(book.getTitle()) && !"".equalsIgnoreCase(book.getTitle()))
                bookdb.setTitle(book.getTitle());

            if (Objects.nonNull(book.getPrice()))
                bookdb.setPrice(book.getPrice());

            if (Objects.nonNull(book.getEdition()) && !"".equalsIgnoreCase(book.getEdition()))
                bookdb.setEdition(book.getEdition());

            bookrepository.save(bookdb);
            return ResponseEntity.status(HttpStatus.OK).body("Book updated successfully" + "\n" + "BookId: " + book.getBookId() + "\n" + "Title: " + book.getTitle() + "\n" + "Author: " + book.getAuthor() + "\n" + "Edition: " + book.getEdition() + "\n" + "ISBN: " + book.getISBN() + "\n" + "Price: " + book.getPrice() + "\n" + "User: " + book.getUser()+ "\n" + "Count: " + book.getCount() + "\n");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with ID "+ bookId+" does not exist, please make a new entry if its a new book.");
        }
    }

    @Override
    public void deleteBook(Long bookId) {
        bookrepository.deleteById(bookId);
    }


    @Override
    public List<Book> findAllBookByKeyword(String keyword) {
        return (List<Book>) bookrepository.findAllBookByKeyword(keyword);
    }


}
