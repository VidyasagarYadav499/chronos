package com.example.chronos.controller;

import com.example.chronos.entity.User;
import com.example.chronos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAll();
    }

    @PostMapping
    public ResponseEntity<?> addNewUser(@RequestBody User user){
        userService.saveEntry(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName){
        User userInDatabase = userService.findByUserName(userName);
        if(userInDatabase != null){
            userInDatabase.setUserName(user.getUserName());
            userInDatabase.setPassword(user.getPassword());
            userService.saveEntry(userInDatabase);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // DELETE: needs to be worked on.
}
