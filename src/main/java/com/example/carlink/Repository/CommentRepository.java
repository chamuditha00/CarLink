package com.example.carlink.Repository;

import com.example.carlink.Entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;


//in video using Integer but in model Comments id define as Long
public interface CommentRepository extends JpaRepository<Comments , Long> {
}
