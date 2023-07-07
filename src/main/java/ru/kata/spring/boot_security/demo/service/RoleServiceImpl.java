package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Set;
@Service
public class RoleServiceImpl implements RoleService{
    private  final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }
    @Override
    @Transactional
    public void addRole(Role role) {
        roleDao.addRole(role);

    }

    @Override
    public Role getRoleById(Long id) {
       return roleDao.getRoleById(id);
    }

    @Override
    public Set<Role> listRoles() {
        return roleDao.listRoles();
    }

    @Override
    public Set<Role> listByRole(List<String> name) {
        return roleDao.listByName(name);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }
}
