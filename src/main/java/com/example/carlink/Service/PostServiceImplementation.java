package com.example.carlink.Service;

import com.example.carlink.Dto.UserDto;
import com.example.carlink.Entity.Post;
import com.example.carlink.Entity.Profile;
import com.example.carlink.Exceptions.PostException;
import com.example.carlink.Exceptions.UserException;
import com.example.carlink.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImplementation implements PostService{

    @Autowired
    private PostRepository postRepository;

    private UserService userService;
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
        return null;
    }

    @Override
    public List<Post> findPostByUserId(Long userId) throws UserException {
        return null;
    }

    @Override
    public Post findPostById(Long postId) throws PostException {
        return null;
    }

    @Override
    public List<Post> findPostByUserIds(List<Long> userIds) throws PostException, UserException {
        return null;
    }

    @Override
    public String savedPost(Long postId, Long userId) throws UserException, PostException {
        return null;
    }

    @Override
    public String unSavedPost(Long postId, Long userId) throws UserException, PostException {
        return null;
    }

    @Override
    public Post likePost(Long postId, Long userId) throws UserException, PostException {
        return null;
    }

    @Override
    public Post unLikePost(Long postId, Long userId) throws UserException, PostException {
        return null;
    }
}
