package com.example.demo;


// This is a book reselling application
// So the basic operation of the system is that the system will ask for the user to create a new user
// If the user is in the records he/she can use the application
// Each book has a different entry in the system, even if there are different copies of the same book all will have different entries in the database
// First the book needs to be sold by a user, which will have a count of 1 in the database
// Another user can buy a book if the count of the book is 1 using the bookId
// There is a search option where the user can search a book by any keyword, and will get a result of all the available options in the book table
// User can resell the book which he/she bought previously from the system using the book id
// There are conditions which will be checked before a user can buy or sell a book, which are performed in the BookServiceImpl file

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
