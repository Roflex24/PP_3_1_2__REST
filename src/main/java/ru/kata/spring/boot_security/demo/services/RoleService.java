package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.entity.Role;

import java.util.List;

public interface RoleService {
      List<Role> findAllRoles();
}
