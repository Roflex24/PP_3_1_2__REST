package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    void create(User user);
    User findUserById(long id);
    void update(long id, User user);
    void delete(long id);
    List<User> findAllUsers();
    User findUserByUsername(String username);
}
