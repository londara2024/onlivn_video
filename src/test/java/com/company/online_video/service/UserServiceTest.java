package com.company.online_video.service;

import com.company.online_video.dto.UsersDTO;
import com.company.online_video.entity.Users;
import com.company.online_video.mapper.UsersMappers;
import com.company.online_video.repository.UsersRepository;
import com.company.online_video.service.serviceImpl.UsersServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UsersRepository usersRepository;
    @Mock
    private UsersMappers usersMappers;
    @Mock
    private PasswordEncoder passwordEncoder;
    private UsersService usersService;

    @BeforeEach
    public void setUp() {
        usersService = new UsersServiceImpl(usersRepository, usersMappers, passwordEncoder);
    }

    @Test
    public void createUserTest() {
        // Given
        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setUsername("username");
        usersDTO.setPassword("password");
        usersDTO.setGender("male");
        usersDTO.setEmail("test@gmail.com");
        usersDTO.setPhoneNumber("096 3883 3333");
        usersDTO.setJoinDate(LocalDateTime.of(2024, 1, 1,9,00,00));
        usersDTO.setDateOfBrith(LocalDateTime.of(2024, 1, 1,9,00,00));


        Users users = new Users();
        users.setId(1L);
        users.setUsername(usersDTO.getUsername());
        users.setPassword(passwordEncoder.encode(usersDTO.getPassword()));
        users.setGender(usersDTO.getGender());
        users.setEmail(usersDTO.getEmail());
        users.setPhoneNumber(usersDTO.getPhoneNumber());
        users.setJoinDate(usersDTO.getJoinDate());
        users.setDateOfBrith(usersDTO.getDateOfBrith());

        // When
        when(usersMappers.toUsers(any(UsersDTO.class))).thenReturn(users);
        when(usersRepository.save(any(Users.class))).thenReturn(users);
        Users use = usersService.createUser(usersDTO);

        // Then
        assertEquals("username", use.getUsername());
        assertEquals(passwordEncoder.encode("password"), use.getPassword());
        assertEquals(1L, users.getId());
    }

    @Test
    public void getUserByIdTest() {
        // Given
        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setUsername("username");
        usersDTO.setPassword("password");
        usersDTO.setGender("male");
        usersDTO.setEmail("test@gmail.com");
        usersDTO.setPhoneNumber("096 3883 3333");
        usersDTO.setJoinDate(LocalDateTime.of(2024, 1, 1,9,00,00));
        usersDTO.setDateOfBrith(LocalDateTime.of(2024, 1, 1,9,00,00));


        Users users = new Users();
        users.setId(1L);
        users.setUsername(usersDTO.getUsername());
        users.setPassword(passwordEncoder.encode(usersDTO.getPassword()));
        users.setGender(usersDTO.getGender());
        users.setEmail(usersDTO.getEmail());
        users.setPhoneNumber(usersDTO.getPhoneNumber());
        users.setJoinDate(usersDTO.getJoinDate());
        users.setDateOfBrith(usersDTO.getDateOfBrith());

        // when
        when(usersRepository.findById(1L)).thenReturn(Optional.of(users));
        Users user = usersService.getUserById(1L);

        // then
        assertEquals(1L, user.getId());
        assertEquals("username", user.getUsername());
        assertEquals(passwordEncoder.encode("password"), user.getPassword());
        assertEquals("test@gmail.com", user.getEmail());

    }
}
