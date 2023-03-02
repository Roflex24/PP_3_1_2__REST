package ru.kata.spring.boot_security.demo.repositories;

import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserRepository {

    User findUserById(long id);

    void create(User user);

    User findUserByUsername(String username);

    List<User> findAllUsers();

    void delete(long id);

    void update(long id, User user);

}
