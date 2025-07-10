package com.benhur.user_management.controller;

import com.benhur.user_management.dto.UserRequest;
import com.benhur.user_management.entity.User;
import com.benhur.user_management.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserRequest user){
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return userService.getAllUser();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody @Valid UserRequest user){
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }

}
