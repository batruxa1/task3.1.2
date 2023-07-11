package ru.itmentor.spring.boot_security.demo.service;



import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void add(User user);
    User getUserById(int id);
    void updateUser(User updateUser);
    void deleteUser(int id);
    User findByUserName(String name);
    User getUserByEmail(String email);
}

