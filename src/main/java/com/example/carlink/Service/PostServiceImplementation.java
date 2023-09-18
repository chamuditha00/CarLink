package com.example.carlink.Service;

import com.example.carlink.Dto.UserDto;
import com.example.carlink.Entity.Post;
import com.example.carlink.Entity.Profile;
import com.example.carlink.Exceptions.PostException;
import com.example.carlink.Exceptions.UserException;
import com.example.carlink.Repository.PostRepository;
import com.example.carlink.Repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImplementation implements PostService{

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserServiceimplementation userService;

    @Autowired
    private ProfileRepository profileRepository;
    @Override
    public Post create(Post post,long userId) throws UserException {
      Profile user = userService.findUserById(userId);

        UserDto userDto = new UserDto();

        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setProfileImageUri(user.getProfileImageUri());
        userDto.setUsername(user.getUsername());
        userDto.setUserId(user.getUserId());

        post.setUser(userDto);

        Post createdPost = postRepository.save(post);
        return createdPost;
    }

    @Override
    public String deletePost(Long postId, Long userId) throws UserException, PostException {
        Post post = findPostById(postId);
        Profile user = userService.findUserById(userId);

        if(post.getUser().getUserId() == user.getUserId()){
            postRepository.deleteById(post.getPostId());
            return "Post deleted successfully";

    }
        throw new PostException("You are not authorized to delete this post");

    }

    @Override
    public List<Post> findPostByUserId(Long userId) throws UserException {
        List<Post> posts = postRepository.findByUserId(userId);
        if(posts.size()==0){
            throw new UserException("This User does not have any post");
        }
        return posts;
    }

    @Override
    public Post findPostById(Long postId) throws PostException {
        Optional<Post> opt = postRepository.findById(postId);

        if(opt.isPresent()){
            return opt.get();
        }
        throw new PostException("Post not found with id: " + postId);
    }

    @Override
    public List<Post> findPostByUserIds(List<Long> userIds) throws PostException, UserException {
        List<Post> posts = postRepository.findAllPostByUserIds(userIds);

        if(posts.size() == 0){
            throw new PostException("No post available");
        }
        return posts;
    }

    @Override
    public String savedPost(Long postId, Long userId) throws UserException, PostException {
        Post post = findPostById(postId);

        Profile user = userService.findUserById(userId);

        if(!user.getSavedposts().contains(post)){
            user.getSavedposts().add(post);
            profileRepository.save(user);



        }

        return "Post saved successfully";
    }

    @Override
    public String unSavedPost(Long postId, Long userId) throws UserException, PostException {
        Post post = findPostById(postId);

        Profile user = userService.findUserById(userId);

        if (!user.getSavedposts().contains(post)) {
            user.getSavedposts().remove(post);
            profileRepository.save(user);




        }

        return "Post remove successfully";
    }

    @Override
    public Post likePost(Long postId, Long userId) throws UserException, PostException {
        Post post = findPostById(postId);
        Profile user = userService.findUserById(userId);
        UserDto userDto = new UserDto();

            userDto.setEmail(user.getEmail());
            userDto.setName(user.getName());
            userDto.setProfileImageUri(user.getProfileImageUri());
            userDto.setUsername(user.getUsername());
            userDto.setUserId(user.getUserId());

            post.getLikes().add(userDto);

        return postRepository.save(post);
        }


    @Override
    public Post unLikePost(Long postId, Long userId) throws UserException, PostException {
        Post post = findPostById(postId);
        Profile user = userService.findUserById(userId);
        UserDto userDto = new UserDto();

        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setProfileImageUri(user.getProfileImageUri());
        userDto.setUsername(user.getUsername());
        userDto.setUserId(user.getUserId());

        post.getLikes().remove(userDto);

        return postRepository.save(post);
    }
}
