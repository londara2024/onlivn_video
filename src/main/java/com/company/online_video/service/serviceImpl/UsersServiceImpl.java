package com.company.online_video.service.serviceImpl;

import com.company.online_video.config.security.Jwt.AuthUser;
import com.company.online_video.dto.UsersDTO;
import com.company.online_video.entity.Permissions;
import com.company.online_video.entity.Roles;
import com.company.online_video.entity.Users;
import com.company.online_video.exception.ApiExceptionResponse;
import com.company.online_video.exception.ResultNotFoundException;
import com.company.online_video.mapper.UsersMappers;
import com.company.online_video.repository.UsersRepository;
import com.company.online_video.service.UsersService;
import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final UsersMappers usersMappers;

    private final PasswordEncoder encoder;


//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//       Users users = usersRepository.findByUsername(username)
//                .orElseThrow(()->new UsernameNotFoundException(username + " not found"));
//
//        return AuthUser.builder()
//                .username(users.getUsername())
//                .password(users.getPassword())
//                .authorities(grantedAuthorities(users.getRoles()))
//                .build();
//    }

    @Override
    public Users createUser(UsersDTO usersDTO) {
        usersDTO.setPassword(encoder.encode(usersDTO.getPassword()));
        Users users = usersMappers.toUsers(usersDTO);
        return usersRepository.save(users);
    }

    @Override
    public Users getUserById(Long userId) {
        return usersRepository.findById(userId)
                .orElseThrow(()-> new ResultNotFoundException("User", String.valueOf(userId)));
    }

    @Override
    public Optional<AuthUser> findUserByUsername(String username) {

        Users users = usersRepository.findByUsername(username)
                .orElseThrow(() -> new ApiExceptionResponse("User " + username + " does not exist", HttpStatus.NOT_FOUND));

        AuthUser authUser = AuthUser.builder()
                .username(users.getUsername())
                .password(users.getPassword())
                .authorities(grantedAuthorities(users.getRoles()))
                .build();

        return Optional.ofNullable(authUser);
    }

    private Set<SimpleGrantedAuthority> grantedAuthorities(Set<Roles> roles) {

        Set<SimpleGrantedAuthority> authorityRole = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRolesName()))
                .collect(Collectors.toSet());

        Set<SimpleGrantedAuthority> authorityPermission = roles.stream()
                .flatMap(this::toStream)
                .collect(Collectors.toSet());

        authorityPermission.addAll(authorityRole);
        return authorityPermission;

//        Set<Set<Permissions>> permission1 = users.getRoles().stream()
//                .map(Roles::getPermissions)
//                .collect(Collectors.toSet());
//
//        Set<Permissions> permissions = permission1.stream().flatMap(Set::stream)
//                .collect(Collectors.toSet());
//
//        Set<SimpleGrantedAuthority> grantedAuthorities = permissions.stream()
//                .map(permission -> new SimpleGrantedAuthority(permission.getPermissionName()))
//                .collect(Collectors.toSet());
//
//        String roleName = users.getRoles().stream()
//                .map(Roles::getRolesName).toString();
//
//        SimpleGrantedAuthority role = new SimpleGrantedAuthority("ROLE_" + roleName);
//        grantedAuthorities.add(role);
//        log.info("{}", grantedAuthorities);
//        return grantedAuthorities;
    }

    private Stream<SimpleGrantedAuthority> toStream(Roles role) throws IOException {
        return role.getPermissions().stream()
                .map(permissions -> new SimpleGrantedAuthority(permissions.getPermissionName()));
    }
}
