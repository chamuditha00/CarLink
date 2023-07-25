package com.example.carlink.Service;

import com.example.carlink.Entity.Story;
import com.example.carlink.Exceptions.StoryException;
import com.example.carlink.Exceptions.UserException;

import java.util.List;

public interface StoryService {

    public Story createStory(Story story, Long userId) throws UserException;
    public List<Story> findStoryByUserId(Long userId) throws UserException, StoryException;
}
