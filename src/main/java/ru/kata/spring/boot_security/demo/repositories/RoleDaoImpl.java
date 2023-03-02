package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entity.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    public RoleDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Role> findAllRoles() {
        return entityManager.createQuery("select role from Role role", Role.class).getResultList();
    }

}
