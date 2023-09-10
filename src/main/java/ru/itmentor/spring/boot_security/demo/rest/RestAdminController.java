package ru.itmentor.spring.boot_security.demo.rest;

import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class RestAdminController {
    private final UserService userService;

    public RestAdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> showAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public User readUser(@PathVariable long id) {
        User user = userService.getUserById(id);

        return user;
    }

    @PostMapping("/user")
    public User addNewUser(@RequestBody User user) {
        userService.add(user);
        return user;
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return user;
    }

    @DeleteMapping("/user/{id}")
    private void deleteUser(@PathVariable long id) {
        
        userService.deleteUser(id);

    }

}