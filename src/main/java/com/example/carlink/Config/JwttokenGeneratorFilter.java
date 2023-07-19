package com.example.carlink.Config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class JwttokenGeneratorFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        Authentication authentication = SecurityContext.getContext();
        if (authentication != null) {
            SecretKey key = Keys.hmacShaKeyFor(SecurityContext.JWT_KEY.getBytes());
            String jwt = Jwts.builder().setIssuer("carlink")
                    .setIssuedAt(new Date())
                    .claim("authorities", populateAuthorities(authentication.getAuthorities()))
                    .claim("username", authentication.getName())
                    .setExpiration(new Date(new Date().getTime() + 1000 * 60 * 60 * 10))
                    .signWith(null,key).compact();

            response.setHeader(SecurityContext.HEADER,jwt);




        }
        filterChain.doFilter(request, response);

    }

    public String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
        Set<String> authorities = new HashSet<>();
        for (GrantedAuthority authority : collection) {
            authorities.add(authority.getAuthority());
        }
        return String.join(",", authorities);
    }

    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/signin");
    }

}
