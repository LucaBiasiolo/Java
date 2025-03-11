package com.diningreview.controllers;

import com.diningreview.entities.User;
import com.diningreview.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.save(user);
    }

    @GetMapping
    public Iterable<User> findAll(){
        return userService.findAll();
    }
}
