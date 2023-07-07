package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Service

public class UserServiceImp implements UserService {
    private final UserDao userDao;
    private  final RoleDao roleDao;

    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    @Autowired
    public UserServiceImp(RoleDao roleDao, UserDao userDao) {
        this.roleDao = roleDao;
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        User userPrimary = userDao.getUserByName(user.getUsername());
        if(userPrimary != null) {
            user.setPassword(bCryptPasswordEncoder().encode(user.getPassword()));
        }
        userDao.addUser(user);

    }

    @Override
    @Transactional
    public void deleteUser(Long id) {

        userDao.deleteUser(id);
    }

    @Override
    @Transactional
    public void editUser(User user) {
        User userPrimary = getUserById(user.getId());
        System.out.println(userPrimary);
        System.out.println(user);
        if(!userPrimary.getPassword().equals(user.getPassword())) {
            user.setPassword(bCryptPasswordEncoder().encode(user.getPassword()));
        }
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

