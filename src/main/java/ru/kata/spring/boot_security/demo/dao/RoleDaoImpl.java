package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImpl implements  RoleDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void addRole(Role user) {
        entityManager.persist(user);
    }

    @Override
    public Role getRoleById(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public Set<Role> listRoles() {
        List<Role> query = entityManager.createQuery("FROM User",Role.class).getResultList();
        return  new HashSet<>(query);
    }
    @Override
    public Role getRoleByName(String name) {
        return entityManager.createQuery("select u from Role u where u.role = :id", Role.class)
                .setParameter("id", name)
                .getResultList().stream().findAny().orElse(null);
    }

    @Override
    public Set<Role> listByName(List<String> name) {
        List<Role> query = entityManager.createQuery("select u from Role u where u.role in (:id)", Role.class)
                .setParameter("id", name)
                .getResultList();
        return new HashSet<>(query);
    }


}
