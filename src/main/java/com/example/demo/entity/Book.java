package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    @Digits(integer = 13, fraction = 0, message = "Enter a valid 13 digit ISBN")
    private Long ISBN;
    @NotBlank
    private String author;
    @NotBlank
    private String title;
    @NotBlank
    private String edition;
    @Positive(message = "Price should be positive value.")
    private float price;
    @Builder.Default
    private int flag = 1;
    private int count = 0;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

}
