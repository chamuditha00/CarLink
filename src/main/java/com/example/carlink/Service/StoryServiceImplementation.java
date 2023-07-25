package com.example.carlink.Service;

import com.example.carlink.Dto.UserDto;
import com.example.carlink.Entity.Profile;
import com.example.carlink.Entity.Story;
import com.example.carlink.Exceptions.StoryException;
import com.example.carlink.Exceptions.UserException;
import com.example.carlink.Repository.ProfileRepository;
import com.example.carlink.Repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StoryServiceImplementation implements StoryService {

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Story createStory(Story story, Long userId) throws UserException {
        Profile user = userService.findUserById(userId);

        UserDto userDto = new UserDto();


        /////////////////////////////////      video use Id instead of userId
        userDto.setEmail(user.getEmail());
        userDto.setUserId(user.getUserId());
        userDto.setName(user.getName());
        userDto.setProfileImageUri(user.getProfileImageUri());
        userDto.setUsername(user.getUsername());

        story.setUser(userDto);

        story.setTimestamp(LocalDateTime.now());

        user.getStories().add(story);

        return storyRepository.save(story);
    }

    @Override
    public List<Story> findStoryByUserId(Long userId) throws UserException, StoryException {

        Profile user = userService.findUserById(userId);
        List<Story> stories = user.getStories();

        if(stories.size()==0){
            throw new StoryException("This User doesn't have any story");
        }


        return stories;
    }




}
