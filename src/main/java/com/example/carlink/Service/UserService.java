package com.example.carlink.Service;

import com.example.carlink.Dto.UserDto;
import com.example.carlink.Entity.Profile;
import com.example.carlink.Exceptions.UserException;
import com.example.carlink.Repository.ProfileRepository;
import com.example.carlink.Security.JwtTokenClaims;
import com.example.carlink.Security.JwtTokenProvider;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ProfileRepository profileRepository;
    private JwtTokenProvider jwtTokenProvider;

    public Profile registerUser(Profile user) throws UserException {
        Optional<Profile> isEmailExist = profileRepository.findByEmail(user.getEmail());
        if(isEmailExist.isPresent()){
            throw new UserException("Email already exist");
        }
        Optional<Profile> isUsernameExist = profileRepository.findByUsername(user.getUsername());
        if(isUsernameExist.isPresent()){
            throw new UserException("Username already taken");
        }
        if(user.getEmail()==null || user.getPassword()==null || user.getName()==null){
            throw new UserException("all filed are required");
        }
        Profile newProfle = new Profile();
        newProfle.setEmail(user.getEmail());
        newProfle.setPassword(passwordEncoder.encode(user.getPassword()));
        newProfle.setName(user.getName());
        newProfle.setUsername(user.getUsername());
        newProfle.setProfileImageUri(user.getProfileImageUri());
        newProfle.setBio(user.getBio());
        newProfle.setGender(user.getGender());

        return profileRepository.save(newProfle);
    }

    public Profile findUserById(long id) throws UserException {
        Optional<Profile> isUserExist = profileRepository.findByUserId(id);
        if(isUserExist.isPresent()){
            return isUserExist.get();
        }
        throw new UserException("User not found" + id);
    }

    public Profile findUserProfile(String token) throws UserException {

        token = token.substring(7);

        JwtTokenClaims jwtTokenClaims = jwtTokenProvider.getClaimsFromToken(token);

        String email = jwtTokenClaims.getUsername();

        Optional<Profile> opt = profileRepository.findByEmail(email);

        if(opt.isPresent()){
            return opt.get();
        }
        throw new UserException("invalid token");
    }

    public Profile findUserByUsername(String username) throws UserException {
        Optional<Profile> isUserExist = profileRepository.findByUsername(username);
        if(isUserExist.isPresent()){
            return isUserExist.get();
        }
        throw new UserException("User not found" + username);
    }

    public String followerUser(long reqUserId, long followUserId) throws UserException {

        Profile reqUser = findUserById(reqUserId);
        Profile followUser = findUserById(followUserId);

        UserDto follower = new UserDto();
        follower.setEmail(reqUser.getEmail());
        follower.setName(reqUser.getName());
        follower.setProfileImageUri(reqUser.getProfileImageUri());
        follower.setUserId(reqUser.getUserId());
        follower.setUsername(reqUser.getUsername());
        //maybe you have to followUser replace with follower
        UserDto following = new UserDto();
        following.setEmail(following.getEmail());
        following.setName(following.getName());
        following.setProfileImageUri(following.getProfileImageUri());
        following.setUserId(following.getUserId());
        following.setUsername(following.getUsername());

        reqUser.getFollowing().add(following);
        followUser.getFollower().add(follower);

        profileRepository.save(reqUser);
        profileRepository.save(followUser);


        return "you are following now " + followUser.getName();
    }

    public String unfollowUser(long reqUserId, long followUserId) throws UserException {

        Profile reqUser = findUserById(reqUserId);
        Profile followUser = findUserById(followUserId);

        UserDto follower = new UserDto();
        follower.setEmail(reqUser.getEmail());
        follower.setName(reqUser.getName());
        follower.setProfileImageUri(reqUser.getProfileImageUri());
        follower.setUserId(reqUser.getUserId());
        follower.setUsername(reqUser.getUsername());
        //maybe you have to followUser replace with follower
        UserDto following = new UserDto();
        following.setEmail(following.getEmail());
        following.setName(following.getName());
        following.setProfileImageUri(following.getProfileImageUri());
        following.setUserId(following.getUserId());
        following.setUsername(following.getUsername());

        reqUser.getFollowing().remove(following);
        followUser.getFollower().remove(follower);

        profileRepository.save(reqUser);
        profileRepository.save(followUser);
        return "you have unfollowed " + followUser.getName();
    }

    public List<Profile> findUserByIds(List<Long> ids) throws UserException {
        List<Profile> profiles = profileRepository.findAllProfileByUserIds(ids);

        return profiles;
    }

    public List<Profile> searchUser(String query) throws UserException {
        List<Profile> profiles = profileRepository.findByQuery(query);
        if(profiles.size()>0){
            return profiles;
        }
        return profiles;
    }

    public Profile updateUser(Profile updateUser, Profile PresentUser) throws UserException {
        if(updateUser.getEmail()!=null){
            PresentUser.setEmail(updateUser.getEmail());
        }
        if(updateUser.getName()!=null){
            PresentUser.setName(updateUser.getName());
        }
        if(updateUser.getBio()!=null){
            PresentUser.setBio(updateUser.getBio());
        }
        if(updateUser.getGender()!=null){
            PresentUser.setGender(updateUser.getGender());
        }
        if(updateUser.getProfileImageUri()!=null){
            PresentUser.setProfileImageUri(updateUser.getProfileImageUri());
        }
        if(updateUser.getPassword()!=null){
            PresentUser.setPassword(updateUser.getPassword());
        }
        if(updateUser.getUsername()!=null){
            PresentUser.setUsername(updateUser.getUsername());
        }
        if (updateUser.getUserId() == PresentUser.getUserId()){
            return profileRepository.save(PresentUser);
        }
        throw new UserException("you are not authorized to update this user");
    }


}
