package com.example.agent.service;

import com.example.agent.domain.User;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserServiceTest {

    private final UserService userService = new UserService();

    @Test
    void create_returnsUserWithGeneratedId() {
        User user = userService.create("Alice", "alice@example.com");

        assertThat(user.id()).isNotBlank();
        assertThat(user.name()).isEqualTo("Alice");
        assertThat(user.email()).isEqualTo("alice@example.com");
    }

    @Test
    void getById_withExistingId_returnsUser() {
        User created = userService.create("Bob", "bob@example.com");

        User fetched = userService.getById(created.id());

        assertThat(fetched).isEqualTo(created);
    }

    @Test
    void getById_withUnknownId_throws() {
        assertThatThrownBy(() -> userService.getById("no-such-id"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
