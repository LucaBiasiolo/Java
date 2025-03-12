package com.diningreview.controllers;

import com.diningreview.entities.User;
import com.diningreview.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping("/{username}")
    public User findUserByUsername(@PathVariable String username){
        return userService.findByUsername(username);
    }

    @PostMapping("/login")
    public User login(@RequestParam String username, @RequestParam String password){
        return userService.findByUsernameAndPassword(username, password);
    }
}
