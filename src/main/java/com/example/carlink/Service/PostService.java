package com.example.carlink.Service;

import com.example.carlink.Entity.Post;
import com.example.carlink.Exceptions.PostException;
import com.example.carlink.Exceptions.UserException;
import java.util.List;

public interface PostService {
    public Post create(Post post, long userId) throws UserException;

    public String deletePost(Long postId, Long userId) throws UserException, PostException;

    public List<Post> findPostByUserId(Long userId) throws UserException;

    public Post findPostById(Long postId) throws PostException;

    public List<Post> findPostByUserIds(List<Long> userIds) throws PostException,UserException;

    public String savedPost(Long postId, Long userId) throws UserException, PostException;

    public String unSavedPost(Long postId, Long userId) throws UserException, PostException;

    public Post likePost(Long postId, Long userId) throws UserException, PostException;

    public Post unLikePost(Long postId, Long userId) throws UserException, PostException;
}
