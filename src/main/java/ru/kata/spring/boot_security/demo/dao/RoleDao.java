package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao {
    void addRole(Role user);
    Role getRoleById(Long id);
    Set<Role> listRoles();
    Role getRoleByName (String name);
    Set<Role> listByName(List<String> name);

}
