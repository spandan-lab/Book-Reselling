package com.example.demo.controller;


import com.example.demo.entity.User;
import com.example.demo.service.UserService;
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

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user-new")
    public ResponseEntity<String> saveUser(
            @RequestBody @Valid User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/user/all")
    public List<User> getBooks() {
        return userService.fetchUsers();
    }

    @ApiOperation(value = "Get User by keyword")
    @GetMapping("/users-keyword")
    public List<User> findAllUserByKeyword(@RequestParam String keyword) {
        return userService.findAllUserByKeyword(keyword);
    }

    @PutMapping("/user/{id}")
    public User updateUser(
            @RequestBody User user, @PathVariable("id") Long userId) {
        return userService.updateUser(user, userId);
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(
            @PathVariable("id") Long userId) {
        userService.deleteUser(userId);
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
