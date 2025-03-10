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
    public User createUser(User user){
        return userService.save(user);
    }

    @GetMapping
    public User findByUsername(@RequestParam String username){
        return userService.findByUsername(username);
    }
}
