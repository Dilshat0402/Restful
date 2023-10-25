package com.dilshat.restfulwebservices.controllers;

import com.dilshat.restfulwebservices.Exceptions.UserNotFoundException;
import com.dilshat.restfulwebservices.dao.UserDaoService;
import com.dilshat.restfulwebservices.models.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    private UserDaoService service;

    private UserController(UserDaoService service){
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id){
        User user = service.getUserById(id);
        if (user == null) {
            throw new UserNotFoundException("id: " + id);
        }
        return user;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id){
        service.deleteUserById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User saveUser = service.createUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(saveUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

}
