package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user, String roleAdmin);

    void deleteUser(Long id);

    void editUser(User user, String roleAdmin);

    User getUserById(Long id);

    List<User> getAllUsers();

    User getUserByName(String username);
}

