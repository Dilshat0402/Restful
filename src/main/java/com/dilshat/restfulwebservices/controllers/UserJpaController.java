package com.dilshat.restfulwebservices.controllers;

import com.dilshat.restfulwebservices.Exceptions.UserNotFoundException;
import com.dilshat.restfulwebservices.models.User;
import com.dilshat.restfulwebservices.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaController {
    private UserRepository userRepository;

    public UserJpaController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("/jpa/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


    //link to /users
    //WebMvcLinkBuilder
    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> getUser(@PathVariable Long id){
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("id: " + id);
        }

        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User saveUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(saveUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

}
