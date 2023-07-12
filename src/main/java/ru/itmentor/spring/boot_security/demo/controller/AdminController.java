package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.RoleService;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String getAllUsers(Model model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("users", allUsers);

        return "users";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user";
    }

//    @GetMapping("/new")
//    public String newUser(Model model) {
//        model.addAttribute("user", new User());
//        return "new";
//    }
//
//    @PostMapping()
//    public String create(@ModelAttribute("user") User user) {
//        userService.add(user);
//        return "redirect:/admin";
//    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute(new User());
        List<Role> roles = roleService.roleList();
        model.addAttribute("allRoles", roles);
        return "new";
    }

    @PostMapping()
    public String addUser(@Validated(User.class) @ModelAttribute("user") User user,
                          @RequestParam("authorities") List<String> values,
                          BindingResult result) {
        if(result.hasErrors()) {
            return "error";
        }
        Set<Role> roles = userService.getSetOfRoles(values);
        user.setRoles(roles);
        userService.add(user);
        return "redirect:/admin";
    }

//    @GetMapping("edit/{id}")
//    public String edit(Model model, @PathVariable("id") int id) {
//        model.addAttribute("user", userService.getUserById(id));
//        return "edit";
//    }
//
//    @PostMapping("{id}")
//    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id) {
//        userService.updateUser(user);
//        return "redirect:/admin";
//    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        List<Role> roles = roleService.roleList();
        model.addAttribute("allRoles", roles);
        return "edit";
    }

    @PostMapping("{id}")
    public String updateUser(@PathVariable("id") int id, User user,
                             BindingResult result,
                             @RequestParam("authorities") List<String> values) {
        if (result.hasErrors()) {
            user.setId(id);
            return "edit";
        }
        Set<Role> roleSet = userService.getSetOfRoles(values);
        user.setRoles(roleSet);
        userService.updateUser(user);
        return "redirect:/admin";
    }


    @PostMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") int id){
        userService.deleteUser(id);
        return "redirect:/admin";
    }

}