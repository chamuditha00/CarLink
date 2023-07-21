package com.example.carlink.Service;

import com.example.carlink.Entity.Profile;
import com.example.carlink.Repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserUserDetailsService implements UserDetailsService {
    @Autowired
    private ProfileRepository profileRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Profile> user = profileRepository.findByEmail(username);
        if(user.isPresent()){
            Profile profile = user.get();

            List<GrantedAuthority> authorities = new ArrayList<>();
            return new User(
                    profile.getEmail(),
                    profile.getPassword(),
                    authorities);

        }
        throw new BadCredentialsException("User not found");


    }
}
