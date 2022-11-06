package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userrepository;

    @Override
    public ResponseEntity<String> saveUser(User user) {
        Optional<User> user_email = userrepository.findUserByEmailId(user.getEmailId());
        if (!user_email.isPresent()) {
            userrepository.save(user);
            return new ResponseEntity<>("User added successfully", HttpStatus.OK);
//            return new ResponseEntity<>("User already exist",HttpStatus.ALREADY_REPORTED);
        }
        return new ResponseEntity<>("User already exist", HttpStatus.ALREADY_REPORTED);


    }

    @Override
    public void deleteUser(Long userId) {
        userrepository.deleteById(userId);
    }

    @Override
    public User updateUser(User user, Long userId) {
        User userdb = userrepository.findById(userId).orElse(null);

        if (Objects.nonNull(user.getFirstName()) && !"".equalsIgnoreCase(user.getFirstName()))
            userdb.setFirstName(user.getFirstName());

        if (Objects.nonNull(user.getLastName()) && !"".equalsIgnoreCase(user.getLastName()))
            userdb.setLastName(user.getLastName());

        if (Objects.nonNull(user.getEmailId()) && !"".equalsIgnoreCase(user.getEmailId()))
            userdb.setEmailId(user.getEmailId());

        if (Objects.nonNull(user.getPhoneNumber()))
            userdb.setPhoneNumber(user.getPhoneNumber());

        return userrepository.save(userdb);
    }

    @Override
    public List<User> findAllUserByKeyword(String keyword) {
        return (List<User>) userrepository.findAllUserByKeyword(keyword);
    }

    @Override
    public List<User> fetchUsers() {
        return (List<User>) userrepository.findAll();
    }
}
