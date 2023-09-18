package com.example.carlink.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserUserDetailService {
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
