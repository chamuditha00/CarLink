package com.example.carlink.Entity;

import com.example.carlink.Dto.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "post")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long postId;

    private String caption;

    private String image;
    private String location;
    private LocalDateTime createAt;

    @Embedded
    @AttributeOverride(name = "userId", column = @Column(name = "user_id"))
    @AttributeOverride(name = "email", column = @Column(name = "email"))
    private UserDto user;


    @OneToMany
    private List<Comments> comments = new ArrayList<Comments>();

    @Embedded
    @ElementCollection
    @JoinTable(name = "likes", joinColumns = @JoinColumn(name = "postid"))
    private Set<UserDto> likes = new HashSet<UserDto>();


}
