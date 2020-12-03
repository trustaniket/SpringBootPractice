package com.learnwithaniket.restfulservices.controller;

import com.learnwithaniket.restfulservices.domain.User;
import com.learnwithaniket.restfulservices.exceptions.UserNotFoundException;
import com.learnwithaniket.restfulservices.repository.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDaoService service;

    @GetMapping(path = "/users")
    public List<User> retrieveAllUser(){
        return service.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public User getUser(@PathVariable int id){
        User user = service.findOne(id);
        if(user == null)
        {
            throw new UserNotFoundException("id-"+id);
        }
        return user;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User user1 = service.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user1.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @DeleteMapping(path = "/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = service.deleteOne(id);
        if(user == null)
        {
            throw new UserNotFoundException("id-"+id);
        }
        ResponseEntity.noContent().build();
    }

}
