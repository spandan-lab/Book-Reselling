package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Getter
@Setter
public class SellNewBookRequest {

    @Positive(message = "userId should be positive value.")
    private Long userId;
    @NotNull
    @Min(value = 1000, message = "enter a valid 4 digit isbn")
    @Max(value = 9999, message = "enter a valid 4 digit isbn")
    private Long isbn;
    @NotBlank
    private String author;
    @NotBlank
    private String title;
    @NotBlank
    private String edition;
    @Positive(message = "Price should be positive value.")
    private float price;
}
