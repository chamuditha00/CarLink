package com.example.carlink.Service;

import com.example.carlink.Entity.Comments;
import com.example.carlink.Exceptions.CommentException;
import com.example.carlink.Exceptions.PostException;
import com.example.carlink.Exceptions.UserException;

public interface CommentService {

    public Comments createComment(Comments comment, Long postId, Long userId) throws UserException, PostException;
    public Comments findCommentById(Long commentId) throws CommentException;
    public Comments likeComment(Long commentId,Long userId) throws CommentException,UserException;
    public Comments unlikeComment(Long commentId,Long userId) throws CommentException,UserException;


}
