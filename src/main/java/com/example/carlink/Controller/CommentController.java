package com.example.carlink.Controller;

import com.example.carlink.Entity.Comments;
import com.example.carlink.Entity.Profile;
import com.example.carlink.Exceptions.CommentException;
import com.example.carlink.Exceptions.PostException;
import com.example.carlink.Exceptions.UserException;
import com.example.carlink.Service.CommentService;
import com.example.carlink.Service.UserServiceimplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserServiceimplementation userService;

    @PostMapping("/create/{postId}")
    public ResponseEntity<Comments> createCommentHandler(@RequestBody Comments comments,@PathVariable Long postId ,@RequestHeader("Authorization") String token) throws UserException, PostException {
        Profile user = userService.findUserProfile(token);
        Comments createdComment = commentService.createComment(comments,postId,user.getUserId());

        return new ResponseEntity<Comments>(createdComment, HttpStatus.OK);
    }

    @PutMapping("/like/{commentId}")
    public ResponseEntity<Comments>likeCommentHandler(@RequestHeader("Authorization") String token, @PathVariable Long commentId) throws UserException, CommentException {
        Profile user = userService.findUserProfile(token);
        System.out.println(user.getUserId());

        Comments comment = commentService.likeComment(commentId,user.getUserId());

        return new ResponseEntity<Comments>(comment, HttpStatus.OK);
    }

    @PutMapping("/unlike/{commentId}")
    public ResponseEntity<Comments>unlikeCommentHandler(@RequestHeader("Authorization") String token, @PathVariable Long commentId) throws UserException, CommentException {
        Profile user = userService.findUserProfile(token);

        Comments comment = commentService.unlikeComment(commentId,user.getUserId());

        return new ResponseEntity<Comments>(comment, HttpStatus.OK);
    }
}
