package com.example.carlink.Controller;

import com.example.carlink.Entity.Profile;
import com.example.carlink.Entity.Story;
import com.example.carlink.Exceptions.StoryException;
import com.example.carlink.Exceptions.UserException;
import com.example.carlink.Service.StoryService;
import com.example.carlink.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stories")
public class StoryController {


    @Autowired
    private UserService userService;

    @Autowired
    private StoryService storyService;

    @PostMapping("/create")
    public ResponseEntity<Story> createStoryHandler(@RequestBody Story story, @RequestHeader("Authorization") String token) throws UserException {
        Profile user = userService.findUserProfile(token);
        Story createStory = storyService.createStory(story,user.getUserId());

        return new ResponseEntity<Story>(createStory, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Story>> findAllStoryByUserIdHandler(@PathVariable Long userId) throws UserException, StoryException {
        Profile user = userService.findUserById(userId);
        List<Story> stories = storyService.findStoryByUserId(user.getUserId());

        return new ResponseEntity<List<Story>>(stories, HttpStatus.OK);
    }
}
