package com.example.carlink.Repository;

import com.example.carlink.Entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    public Optional<Profile> findByUserId(long id);
    public Optional<Profile> findByUsername(String username);
    public Optional<Profile> findByEmail(String email);
    @Query("SELECT p FROM Profile p WHERE p.userId IN :users")
    public List<Profile> findAllProfileByUserIds(@Param("users") List<Long> ids);

    @Query("select distinct p from Profile p where p.username like %:query% or p.name like %:query% or p.email like %:query%")
    public List<Profile> findByQuery(@Param("query") String query);



}
