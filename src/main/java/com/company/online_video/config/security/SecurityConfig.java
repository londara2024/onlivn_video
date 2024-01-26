package com.company.online_video.config.security;

import com.company.online_video.config.security.Jwt.JwtLoginFilter;
import com.company.online_video.config.security.Jwt.TokenVerifyFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .addFilter(new JwtLoginFilter(authenticationManager(authenticationConfiguration)))
                .addFilterAfter(new TokenVerifyFilter(), JwtLoginFilter.class)
                .authorizeHttpRequests(authorization -> authorization
                        .requestMatchers( "/index.html", "css/**", "js/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/video/getAllVideo").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/v1/user/**").hasAuthority(PermissionsEnum.USER_READ.getDescription())
                        .requestMatchers(HttpMethod.POST, "/api/v1/user/**").hasAuthority(PermissionsEnum.USER_WRITE.getDescription())
                        .requestMatchers(HttpMethod.PUT, "/api/v1/user/**").hasAuthority(PermissionsEnum.USER_WRITE.getDescription())
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/user/**").hasAuthority(PermissionsEnum.USER_WRITE.getDescription())
                        .requestMatchers(HttpMethod.GET, "/api/v1/category/**").hasAuthority(PermissionsEnum.CATEGORY_READ.getDescription())
                        .requestMatchers(HttpMethod.POST, "/api/v1/category/**").hasAuthority(PermissionsEnum.CATEGORY_WRITE.getDescription())
                        .requestMatchers(HttpMethod.PUT, "/api/v1/category/**").hasAuthority(PermissionsEnum.CATEGORY_WRITE.getDescription())
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/category/**").hasAuthority(PermissionsEnum.CATEGORY_WRITE.getDescription())
                        .requestMatchers(HttpMethod.GET, "/api/v1/courses/**").hasAuthority(PermissionsEnum.COURSE_READ.getDescription())
                        .requestMatchers(HttpMethod.POST, "/api/v1/courses/**").hasAuthority(PermissionsEnum.COURSE_WRITE.getDescription())
                        .requestMatchers(HttpMethod.PUT, "/api/v1/courses/**").hasAuthority(PermissionsEnum.COURSE_WRITE.getDescription())
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/courses/**").hasAuthority(PermissionsEnum.COURSE_WRITE.getDescription())
                        .requestMatchers(HttpMethod.GET, "/api/v1/video/**").hasAuthority(PermissionsEnum.VIDEO_READ.getDescription())
                        .requestMatchers(HttpMethod.POST, "/api/v1/video/**").hasAuthority(PermissionsEnum.VIDEO_WRITE.getDescription())
                        .requestMatchers(HttpMethod.PUT, "/api/v1/video/**").hasAuthority(PermissionsEnum.VIDEO_WRITE.getDescription())
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/video/**").hasAuthority(PermissionsEnum.VIDEO_WRITE.getDescription())
                        .requestMatchers(HttpMethod.GET, "/api/v1/subscribes/**").hasAuthority(PermissionsEnum.SUBSCRIBES_READ.getDescription())
                        .requestMatchers(HttpMethod.POST, "/api/v1/subscribes/**").hasAuthority(PermissionsEnum.SUBSCRIBES_WRITE.getDescription())
                        .requestMatchers(HttpMethod.PUT, "/api/v1/subscribes/**").hasAuthority(PermissionsEnum.SUBSCRIBES_WRITE.getDescription())
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/subscribes/**").hasAuthority(PermissionsEnum.SUBSCRIBES_WRITE.getDescription())

                        // Login Authentication
                        .requestMatchers(HttpMethod.GET, "/api/v1/permissions/**").hasAuthority(PermissionsEnum.PERMISSIONS_READ.getDescription())
                        .requestMatchers(HttpMethod.POST, "/api/v1/permissions/**").hasAuthority(PermissionsEnum.PERMISSIONS_WRITE.getDescription())
                        .requestMatchers(HttpMethod.PUT, "/api/v1/permissions/**").hasAuthority(PermissionsEnum.PERMISSIONS_WRITE.getDescription())
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/permissions/**").hasAuthority(PermissionsEnum.PERMISSIONS_WRITE.getDescription())
                        .requestMatchers(HttpMethod.GET, "/api/v1/roles/**").hasAuthority(PermissionsEnum.ROLE_READ.getDescription())
                        .requestMatchers(HttpMethod.POST, "/api/v1/roles/**").hasAuthority(PermissionsEnum.ROLE_WRITE.getDescription())
                        .requestMatchers(HttpMethod.PUT, "/api/v1/roles/**").hasAuthority(PermissionsEnum.ROLE_WRITE.getDescription())
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/roles/**").hasAuthority(PermissionsEnum.ROLE_WRITE.getDescription())
                        .requestMatchers(HttpMethod.GET, "/api/v1/rolesPermissions/**").hasAuthority(PermissionsEnum.ROLE_PERMISSIONS_READ.getDescription())
                        .requestMatchers(HttpMethod.POST, "/api/v1/rolesPermissions/**").hasAuthority(PermissionsEnum.ROLE_PERMISSIONS_WRITE.getDescription())
                        .requestMatchers(HttpMethod.PUT, "/api/v1/rolesPermissions/**").hasAuthority(PermissionsEnum.ROLE_PERMISSIONS_WRITE.getDescription())
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/rolesPermissions/**").hasAuthority(PermissionsEnum.ROLE_PERMISSIONS_WRITE.getDescription())
                        .requestMatchers(HttpMethod.GET, "/api/v1/userRoles/**").hasAuthority(PermissionsEnum.USER_ROLE_READ.getDescription())
                        .requestMatchers(HttpMethod.POST, "/api/v1/userRoles/**").hasAuthority(PermissionsEnum.USER_ROLE_WRITE.getDescription())
                        .requestMatchers(HttpMethod.PUT, "/api/v1/userRoles/**").hasAuthority(PermissionsEnum.USER_ROLE_WRITE.getDescription())
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/userRoles/**").hasAuthority(PermissionsEnum.USER_ROLE_WRITE.getDescription())
                        // End Login Authentication

                        .anyRequest()
                        .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
