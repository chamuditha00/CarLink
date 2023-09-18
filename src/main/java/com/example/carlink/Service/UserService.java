package com.example.carlink.Service;

import com.example.carlink.Entity.Profile;
import com.example.carlink.Exceptions.UserException;

import java.util.List;

public interface UserService {
    public Profile updateUser(Profile updateUser, Profile PresentUser) throws UserException;
    public List<Profile> searchUser(String query) throws UserException;
    public List<Profile> findUserByIds(List<Long> ids) throws UserException;

    public String unfollowUser(long reqUserId, long followUserId) throws UserException;

    public String followerUser(long reqUserId, long followUserId) throws UserException;

    public Profile findUserByUsername(String username) throws UserException;
    public Profile findUserProfile(String token) throws UserException;

    public Profile findUserById(long id) throws UserException;

    public Profile registerUser(Profile user) throws UserException;
    }