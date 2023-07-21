package com.example.carlink.Controller;

import com.example.carlink.Entity.Profile;
import com.example.carlink.Exceptions.UserException;
import com.example.carlink.Repository.ProfileRepository;
import com.example.carlink.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthController {
    @Autowired
    private ProfileRepository ProfileRepository;


    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Profile> registerUserHandler(@RequestBody Profile user) throws UserException {
        Profile newUser = userService.registerUser(user);
        return new ResponseEntity<Profile>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/signin")
    public ResponseEntity<Profile> signinUserHandler(Authentication auth) throws BadCredentialsException{

        Optional<Profile> opt = ProfileRepository.findByEmail(auth.getName());
        if(opt.isPresent()){
                Profile profile = opt.get();
            return new ResponseEntity<Profile>(opt.get(), HttpStatus.ACCEPTED);
        }
        throw new BadCredentialsException("Invalid username or password");

        }
    }


