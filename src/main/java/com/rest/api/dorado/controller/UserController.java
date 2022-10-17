package com.rest.api.dorado.controller;

import com.rest.api.dorado.exception.UserNotFoundException;
import com.rest.api.dorado.model.User;
import com.rest.api.dorado.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/adduser")
    User newUser(@RequestBody User newUser) {

        return userRepo.save(newUser);
    }

    @GetMapping("/allusers")
    List<User> getAllUsers() {
        
        return userRepo.findAll();
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id) {

        return userRepo.findById(id)
                .orElseThrow(()-> new UserNotFoundException(id));
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id) {

        return userRepo.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setSurname(newUser.getSurname());
                    user.setUsername(newUser.getUsername());
                    user.setEmail(newUser.getEmail());
                    return userRepo.save(user);
                }).orElseThrow(()-> new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id) {

        if(!userRepo.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepo.deleteById(id);
        return "The user was deleted";
    }
}
