package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value = "Select * from User u where " +
            "(u.email_id like %?1%) ",
            nativeQuery = true)
    Optional<User> findUserByEmailId(String emailId);

    @Query(value = "Select * from User u where " +
            "(u.first_name like %?1% OR u.last_name like %?1%) ",
            nativeQuery = true)
    public List<User> findAllUserByKeyword(String keyword);
}
