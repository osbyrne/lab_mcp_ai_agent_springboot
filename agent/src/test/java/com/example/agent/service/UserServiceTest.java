package com.example.agent.service;

import com.example.agent.domain.User;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class UserServiceTest {

    private final UserService userService = new UserService();

    @Test
    void findAll_returnsAllUsers() {
        List<User> users = userService.findAll();

        assertThat(users).hasSize(2);
        assertThat(users).extracting(User::name).containsExactly("Alice", "Bob");
    }

    @Test
    void findById_withExistingId_returnsUser() {
        Optional<User> user = userService.findById(1L);

        assertThat(user).isPresent();
        assertThat(user.get().name()).isEqualTo("Alice");
    }

    @Test
    void findById_withUnknownId_returnsEmpty() {
        Optional<User> user = userService.findById(99L);

        assertThat(user).isEmpty();
    }
}
