package ru.itmentor.spring.boot_security.demo.service;



import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    List<User> getAllUsers();
    void add(User user);
    User getUserById(long id);
    void updateUser(User updateUser);
    void deleteUser(long id);
    Set<Role> getSetOfRoles(List<String> id);
    User findByUserName(String name);
    User getUserByEmail(String email);
}

