package com.dilshat.restfulwebservices.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.dilshat.restfulwebservices.Exceptions.UserNotFoundException;
import com.dilshat.restfulwebservices.dao.UserDaoService;
import com.dilshat.restfulwebservices.models.User;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    private UserDaoService service;

    public UserController(UserDaoService service){
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return service.findAll();
    }


    //link to /users
    //WebMvcLinkBuilder
    @GetMapping("/users/{id}")
    public EntityModel<User> getUser(@PathVariable Long id){
        User user = service.getUserById(id);
        if (user == null) {
            throw new UserNotFoundException("id: " + id);
        }

        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
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
