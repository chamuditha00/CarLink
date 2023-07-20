package com.example.carlink.Controller;

import com.example.carlink.Entity.Profile;
import com.example.carlink.Exceptions.UserException;
import com.example.carlink.Response.MessageResponse;
import com.example.carlink.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {


    @Autowired
    private UserService userService;

    @GetMapping("/id/{id}")
    public ResponseEntity<Profile>  findUserByidHandler(@PathVariable("id") long id) throws UserException {
        Profile profile =userService.findUserById(id);
        return new ResponseEntity<Profile>(profile, HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Profile> findUserByUsernameHandler(@PathVariable("username") String username) throws UserException {
        Profile profile = userService.findUserByUsername(username);
        return new ResponseEntity<Profile>(profile, HttpStatus.OK);
    }

    @PutMapping("/follow/{id}")
    public ResponseEntity<MessageResponse> followUserHandler(@PathVariable("id") long id) throws UserException {
       return null;
    }

    @PutMapping("/unfollow/{id}")
    public ResponseEntity<MessageResponse> unfollowUserHandler(@PathVariable("id") long id) throws UserException {
        return null;
    }

    @PutMapping("/request")
    public ResponseEntity<MessageResponse> findUserProfileHandler(@RequestHeader("Authorization") String token) throws UserException {
        // MessageResponse Response = userService.followUser(followUserId, followUserId);
        return null;
    }

    @GetMapping("/m/{ids}")
    public ResponseEntity<List<Profile>> findUserByUserIdHandler(@PathVariable List<Long> ids) throws UserException {
        List<Profile> profiles = userService.findUserByIds(ids);
        return new ResponseEntity<List<Profile>>(profiles, HttpStatus.OK);
    }

    // api/profile/search?q=abc
    @GetMapping("/search")
    public ResponseEntity<List<Profile>> SearchUserHandler(@RequestParam("q") String query) throws UserException {
        List<Profile> profiles = userService.searchUser(query);
        return new ResponseEntity<List<Profile>>(profiles, HttpStatus.OK);
    }

    public ResponseEntity<Profile> updateUserHandler(@RequestHeader("Authorization") String token, @RequestBody Profile profile) throws UserException {
        //Profile updatedProfile = userService.updateUserProfile(token, profile);
        return null;
    }



    }

