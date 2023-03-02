package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public List<User> showAllUsers(){
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public User showUser(@PathVariable("id") long id){
        return userService.findUserById(id);
    }

    @PostMapping()
    public ResponseEntity<User> showCreateUser(@RequestBody User user) {
        userService.create(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> showUpdateUser(@PathVariable("id") long id, @RequestBody User user) {
        userService.update(user.getId(), user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> showDeleteUser(@PathVariable("id") long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> showAllRoles() {
        return new ResponseEntity<>(roleService.findAllRoles(), HttpStatus.OK);
    }
}

