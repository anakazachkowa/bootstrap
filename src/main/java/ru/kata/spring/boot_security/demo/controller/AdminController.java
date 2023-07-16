package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;


import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping(value = "/admin")
    public String getFormAllUsers(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());

        return "allUsersPage";
    }

    @GetMapping(value = "/add")
    public String getUserFormForAdd(@ModelAttribute("user") User user) {

        return "addUser";
    }

    @PostMapping(value = "/add")
    public String addUser(@ModelAttribute("user") User user) {
        userService.addUser(user);

        return "redirect:/admin";
    }

    @GetMapping(value = "/edit/{id}")
    public String getUserFormForUpdate(ModelMap model, @PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        model.addAttribute("roleAdmin", true);
        model.addAttribute("user", user);

        return "redirect:/admin";
    }

    @PutMapping(value = "/edit/{id}")
    public String editUser(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        user.setId(id);
        userService.editUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUser(id);

        return "redirect:/admin";
    }

}

