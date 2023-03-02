package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository{

    @PersistenceContext
    private EntityManager entityManager;

    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User findUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void create(User user) {
        entityManager.persist(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return (User) entityManager.createQuery("select user from User user where user.username = ?1").setParameter(1, username).getSingleResult();
    }

    @Override
    public List findAllUsers() {
        return entityManager.createQuery("select user from User user").getResultList();
    }

    @Override
    public void delete(long id) {
        entityManager.remove(findUserById(id));
    }

    @Override
    public void update(long id, User user) {
        entityManager.merge(user);
    }

}
