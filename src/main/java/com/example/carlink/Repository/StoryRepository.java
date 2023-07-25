package com.example.carlink.Repository;

import com.example.carlink.Entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoryRepository extends JpaRepository<Story, Long> {

    @Query("select s from Story s where s.user=:userId")    // s.user.id
    List<Story> findStoriesByUserId(@Param("userId") Long userId);
}
