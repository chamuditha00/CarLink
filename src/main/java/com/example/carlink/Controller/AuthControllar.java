package com.example.carlink.Controller;

import com.example.carlink.Entity.Profile;
import com.example.carlink.Exceptions.UserException;
import com.example.carlink.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthControllar {


    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Profile> registerUserHandler(@RequestBody Profile user) throws UserException {
        Profile newUser = userService.registerUser(user);
        return new ResponseEntity<Profile>(newUser, HttpStatus.CREATED);
    }


}
