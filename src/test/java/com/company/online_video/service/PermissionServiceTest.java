package com.company.online_video.service;

import com.company.online_video.dto.PermissionDTO;
import com.company.online_video.entity.Permissions;
import com.company.online_video.exception.ResultNotFoundException;
import com.company.online_video.repository.PermissionRepository;
import com.company.online_video.service.serviceImpl.PermissionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PermissionServiceTest {

    @Mock
    private PermissionRepository permissionRepository;

    private PermissionService permissionService;

    @BeforeEach
    public void setUp() {
        permissionService = new PermissionServiceImpl(permissionRepository);
    }

    @Test
    public void repositorySaveTest() {
        // Given
        PermissionDTO permissionDTO = new PermissionDTO();
        permissionDTO.setPermissionName("test");

        // When
        permissionService.createPermissions(permissionDTO);

        // Then
        verify(permissionRepository, times(1)).save(any(Permissions.class));
    }

    @Test
    public void createPermissionTest() {
        // Given
        PermissionDTO permissionDTO = new PermissionDTO();
        permissionDTO.setPermissionName("test");

        Permissions permission = new Permissions();
        permission.setPermissionName(permissionDTO.getPermissionName());
        permission.setId(1L);

        // When
        when(permissionRepository.save(any(Permissions.class))).thenReturn(permission);

        // Then
        assertEquals(permission, permissionService.createPermissions(permissionDTO));
        assertEquals("test", permission.getPermissionName());
        assertEquals(1L, permission.getId());
    }

    @Test
    public void getPermissionTest() {
        // Given
        Permissions permission = new Permissions();
        permission.setPermissionName("test");
        permission.setId(1L);

        // When
        when(permissionRepository.findById(1L)).thenReturn(Optional.of(permission));
        Permissions resultPermission = permissionService.getPermissionsById(1L);

        // Then
        assertEquals(1L, resultPermission.getId());
        assertEquals("test", resultPermission.getPermissionName());
    }

    @Test
    public void testGetPermissionsById() {
        // Given
        Permissions permission = new Permissions();
        permission.setPermissionName("test");
        permission.setId(1L);

        // When
        when(permissionRepository.findById(1L)).thenReturn(Optional.of(permission));
        Permissions resultPermission = permissionService.getPermissionsById(1L);

        // Then
        assertEquals(1L, resultPermission.getId());
        assertEquals("test", resultPermission.getPermissionName());
    }

    @Test
    public void testThrowsException() {
        // Given

        // When
        when(permissionRepository.findById(2L)).thenReturn(Optional.empty());

        // Then
        assertThatThrownBy(() -> permissionService.getPermissionsById(2L))
              .isInstanceOf(ResultNotFoundException.class)
              .hasMessage("Permission with id = " + 2 + " not found");
    }

}
