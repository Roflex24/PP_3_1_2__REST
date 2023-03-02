package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repositories.UserDao;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserDao userDao, @Lazy PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findUserById(long id) {
        return userDao.findUserById(id);
    }

    @Override
    @Transactional
    public void create(User user) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDao.create(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (findUserByUsername(username) == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }
        return findUserByUsername(username);
    }

    @Override
    @Transactional
    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    @Override
    @Transactional
    public void update(long id, User user) {
        if (user.getPassword().isEmpty()) {
            user.setPassword(userDao.findUserByUsername(user.getUsername()).getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userDao.update(id, user);
    }

    @Override
    @Transactional
    public void delete(long id) {
        User user = userDao.findUserById(id);
        if (user != null) {
            userDao.delete(user);
        }
    }
}
