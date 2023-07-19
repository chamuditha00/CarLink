package com.example.carlink.Config;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;




public class SecurityContext {

    public static final String JWT_KEY = "uyefduyewfgyueuieftgewf";
    public static final String HEADER = "Authorization";
    public static Authentication getContext() {
        return SecurityContextHolder.getContext().getAuthentication();
    }




    }


