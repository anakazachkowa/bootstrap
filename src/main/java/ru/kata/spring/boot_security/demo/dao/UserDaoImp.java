package ru.kata.spring.boot_security.demo.dao;


import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class UserDaoImp implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUserByName(String username) {
        return entityManager.createQuery("select u from User u join fetch u.roles where u.username = :username", User.class)
                .setParameter("username", username)
                .getResultList().stream().findAny().orElse(null);
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);

    }

    @Override
    public void deleteUser(Long id) {

        entityManager.remove(getUserById(id));
    }

    @Override
    public void editUser(User user) {

        entityManager.merge(user);
    }

    @Override
    public User getUserById(Long id) {

        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> getAllUsers() {

        return entityManager.createQuery("select s from User s", User.class).getResultList();
    }
}

