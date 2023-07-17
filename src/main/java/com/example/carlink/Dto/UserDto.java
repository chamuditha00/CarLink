package com.example.carlink.Dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {


    private long userId;
    private String ProfileImageUri;
    private String name;
    private String username;
    private String email;

}
