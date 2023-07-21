package com.example.carlink.Repository;
import com.example.carlink.Entity.Post;
import com.example.carlink.Entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>{

    @Query("SELECT p FROM Post p WHERE p.user = ?1")
    public List<Post> findByUserId(Long userId);

    @Query("SELECT p FROM Post p WHERE p.user IN : users ORDER BY p.createAt DESC")

    public List<Post> findAllPostByUserIds(@Param("users")List<Long> userIds);
}
