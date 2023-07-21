package com.example.carlink.Controller;

import com.example.carlink.Entity.Post;
import com.example.carlink.Entity.Profile;
import com.example.carlink.Exceptions.PostException;
import com.example.carlink.Exceptions.UserException;
import com.example.carlink.Response.MessageResponse;
import com.example.carlink.Service.PostService;
import com.example.carlink.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Post> createPostHandler(@RequestBody Post post,@RequestHeader("Authorization") String token) throws UserException {
        Profile user = userService.findUserProfile(token);
        Post createdPost = postService.create(post,user.getUserId());

        return new ResponseEntity<Post>(createdPost, HttpStatus.OK);


    }


    @GetMapping("/all/{id}")
    public ResponseEntity<List<Post>> findPostByUserIdHandler(@PathVariable("id") long userId) throws UserException {

        List<Post> posts = postService.findPostByUserId(userId);

        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);

    }

    @GetMapping("/following/{id}")
    public ResponseEntity<List<Post>> findAllPostByUserIdsHandler(@PathVariable("ids") List<Long> userIds) throws UserException, PostException {

        List<Post> posts = postService.findPostByUserIds(userIds);

        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);

    }


    @GetMapping("/{postId}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable long postId) throws PostException {

        Post post = postService.findPostById(postId);

        return new ResponseEntity<Post>(post, HttpStatus.OK);

    }

    @PutMapping("/like/{postId}")
    public ResponseEntity<Post> likePostHandler(@PathVariable long postId,@RequestHeader("Authorization") String token) throws UserException, PostException {

        Profile user = userService.findUserProfile(token);
        Post likedPost = postService.likePost(postId,user.getUserId());

        return new ResponseEntity<Post>(likedPost, HttpStatus.OK);

    }

    @PutMapping("/unlike/{postId}")
    public ResponseEntity<Post> unlikePostHandler(@PathVariable long postId,@RequestHeader("Authorization") String token) throws UserException, PostException {

        Profile user = userService.findUserProfile(token);
        Post likedPost = postService.unLikePost(postId,user.getUserId());

        return new ResponseEntity<Post>(likedPost, HttpStatus.OK);

    }


    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<MessageResponse> deletePostHandler(@PathVariable long postId,@RequestHeader("Authorization") String token) throws UserException, PostException {

        Profile user = userService.findUserProfile(token);
        String message = postService.deletePost(postId,user.getUserId());

        return new ResponseEntity<MessageResponse>(new MessageResponse(message), HttpStatus.ACCEPTED);

    }

    @PutMapping("/save_post/{postId}")
    public ResponseEntity<MessageResponse> savedPostHandler(@PathVariable long postId,@RequestHeader("Authorization") String token) throws UserException, PostException {

        Profile user = userService.findUserProfile(token);
        String message = postService.savedPost(postId,user.getUserId());

        return new ResponseEntity<MessageResponse>(new MessageResponse(message), HttpStatus.ACCEPTED);

    }

    @PutMapping("/unsave_post/{postId}")
    public ResponseEntity<MessageResponse> unsavedPostHandler(@PathVariable long postId,@RequestHeader("Authorization") String token) throws UserException, PostException {

        Profile user = userService.findUserProfile(token);
        String message = postService.unSavedPost(postId,user.getUserId());

        return new ResponseEntity<MessageResponse>(new MessageResponse(message), HttpStatus.ACCEPTED);

    }

}
