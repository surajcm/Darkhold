package com.quiz.darkhold.login.service;

import com.quiz.darkhold.login.entity.Role;
import com.quiz.darkhold.login.entity.User;
import com.quiz.darkhold.login.repository.RoleRepository;
import com.quiz.darkhold.login.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.powermock.reflect.Whitebox;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServiceImplTest {
    private final UserServiceImpl userService = new UserServiceImpl();
    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private final RoleRepository roleRepository = Mockito.mock(RoleRepository.class);
    private final BCryptPasswordEncoder encoder = Mockito.mock(BCryptPasswordEncoder.class);

    @BeforeEach
    public void setup() {
        Whitebox.setInternalState(userService, "userRepository", userRepository);
        Whitebox.setInternalState(userService, "roleRepository", roleRepository);
        Whitebox.setInternalState(userService, "encoder", encoder);
    }

    @Test
    public void save() {
        when(encoder.encode(anyString())).thenReturn("MockedPassword");
        when(roleRepository.findAll()).thenReturn(mockRoles());
        User user = new User();
        Assertions.assertAll(() -> userService.save(user));
    }

    @Test
    public void findByUsername() {
        String username = "superman";
        when(userRepository.findByUsername(anyString())).thenReturn(mockUser(username));
        User user = userService.findByUsername(username);
        Assertions.assertEquals(username, user.getUsername());
    }

    private List<Role> mockRoles() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role());
        return roles;
    }

    private User mockUser(final String userName) {
        User user = new User();
        user.setUsername(userName);
        return user;
    }
}