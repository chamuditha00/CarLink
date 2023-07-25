package com.example.carlink.Exceptions;

import com.example.carlink.Dto.UserDto;
import com.example.carlink.Entity.Comments;
import com.example.carlink.Entity.Post;
import com.example.carlink.Entity.Profile;
import com.example.carlink.Repository.CommentRepository;
import com.example.carlink.Repository.PostRepository;
import com.example.carlink.Service.CommentService;
import com.example.carlink.Service.PostService;
import com.example.carlink.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentServiceImplementation implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Comments createComment(Comments comment, Long postId, Long userId) throws UserException, PostException {
        Profile user = userService.findUserById(userId);
        //Using Profile instead of User in video

        Post post = postService.findPostById(postId);

        UserDto userDto = new UserDto();

        userDto.setEmail(user.getEmail());

//        ////////////////////////////////////////////////////////////////////////////////////////
//        userDto.setId(user.getId());
        userDto.setUserId(user.getUserId());

        userDto.setName(user.getName());
        userDto.setProfileImageUri(user.getProfileImageUri());
        userDto.setUsername(user.getUsername());

        comment.setUser(userDto);

        /////////////////////////////////////////////////////////////////////
        comment.setCreatedAt(LocalDateTime.now());

        Comments createdComment = commentRepository.save(comment);

        post.getComments().add(createdComment);

        postRepository.save(post);


        return createdComment;
    }

    @Override
    public Comments findCommentById(Long commentId) throws CommentException {

        Optional<Comments> opt = commentRepository.findById(commentId);
        if(opt.isPresent()){
            return  opt.get();
        }

    throw new CommentException("Comment is not Exist with id: "+ commentId);
    }

    @Override
    public Comments likeComment(Long commentId, Long userId) throws CommentException, UserException {

        Profile user = userService.findUserById(userId);
        Comments comment= findCommentById(commentId);

        UserDto userDto = new UserDto();


        userDto.setEmail(user.getEmail());
        userDto.setUserId(user.getUserId());
        userDto.setName(user.getName());
        userDto.setProfileImageUri(user.getProfileImageUri());
        userDto.setUsername(user.getUsername());

        comment.getLikedByUsers().add(userDto);

        return commentRepository.save(comment);
    }

    @Override
    public Comments unlikeComment(Long commentId, Long userId) throws CommentException, UserException {
        Profile user = userService.findUserById(userId);
        Comments comment= findCommentById(commentId);

        UserDto userDto = new UserDto();


        userDto.setEmail(user.getEmail());
        userDto.setUserId(user.getUserId());
        userDto.setName(user.getName());
        userDto.setProfileImageUri(user.getProfileImageUri());
        userDto.setUsername(user.getUsername());

        comment.getLikedByUsers().remove(userDto);

        return commentRepository.save(comment);
    }
}
