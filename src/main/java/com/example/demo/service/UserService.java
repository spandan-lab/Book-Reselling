package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<String> saveUser(User user);

    //ResponseEntity<String> saveUser(User user);

    void deleteUser(Long userId);

    User updateUser(User user, Long userId);

    List<User> findAllUserByKeyword(String keyword);

    List<User> fetchUsers();
}
