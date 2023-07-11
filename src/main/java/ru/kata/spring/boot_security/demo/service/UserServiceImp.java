package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service

public class UserServiceImp implements UserService {
    private final UserDao userDao;

    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImp(UserDao userDao, RoleService roleService, PasswordEncoder passwordEncoder) {

        this.userDao = userDao;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void addUser(User user, String roleAdmin) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleByName("ROLE_USER"));
        if (roleAdmin != null && roleAdmin.equals("ROLE_ADMIN")) {
            roles.add(roleService.getRoleByName("ROLE_ADMIN"));

        }
        user.setRoles(roles);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.addUser(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {

        userDao.deleteUser(id);
    }

    @Override
    @Transactional
    public void editUser(User user, String roleAdmin) {

        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleByName("ROLE_USER"));
        if (roleAdmin != null && roleAdmin.equals("ROLE_ADMIN")) {
            roles.add(roleService.getRoleByName("ROLE_ADMIN"));

        }
        user.setRoles(roles);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.editUser(user);
    }


    @Override
    public User getUserById(Long id) {

        return userDao.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {

        return userDao.getAllUsers();
    }

    @Override
    public User getUserByName(String username) {

        return userDao.getUserByName(username);
    }
}

