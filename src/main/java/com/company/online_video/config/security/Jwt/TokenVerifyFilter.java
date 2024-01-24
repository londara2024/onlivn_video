package com.company.online_video.config.security.Jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class TokenVerifyFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");
        if (Objects.isNull(authorizationHeader) && !authorizationHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
        }

        String token = authorizationHeader.replace("Bearer ", "");
        String secretKey = "22322@!#%$&asdfghddseewerrbnjjuuyttffdswwrfdss";

        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseClaimsJws(token);

        Claims body = claimsJws.getBody();
        String username = body.getSubject();

        List<Map<String,String>> authorities = (List<Map<String,String>>) body.get("authorities");
        log.info("TokenVerifyFilter Token authorities :: {}", authorities);

        Set<SimpleGrantedAuthority> authorization = authorities.stream()
                .map(authen -> new SimpleGrantedAuthority(authen.get("authority")))
                .collect(Collectors.toSet());
        log.info("TokenVerifyFilter Token authorization :: {}", authorization);

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorization);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);

    }
}




































