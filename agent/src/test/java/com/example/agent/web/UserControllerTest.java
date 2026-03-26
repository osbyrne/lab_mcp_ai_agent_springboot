package com.example.agent.web;

import com.example.agent.domain.User;
import com.example.agent.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Test
    void createUser_returnsUser() throws Exception {
        when(userService.create("Alice", "alice@example.com"))
                .thenReturn(new User("uuid-1", "Alice", "alice@example.com"));

        mockMvc.perform(post("/api/users")
                        .param("name", "Alice")
                        .param("email", "alice@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("uuid-1"))
                .andExpect(jsonPath("$.name").value("Alice"))
                .andExpect(jsonPath("$.email").value("alice@example.com"));
    }

    @Test
    void getUser_withExistingId_returnsUser() throws Exception {
        when(userService.getById("uuid-1"))
                .thenReturn(new User("uuid-1", "Alice", "alice@example.com"));

        mockMvc.perform(get("/api/users/uuid-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("uuid-1"))
                .andExpect(jsonPath("$.name").value("Alice"));
    }

    @Test
    void getUser_withUnknownId_returns404() throws Exception {
        when(userService.getById("no-such-id"))
                .thenThrow(new IllegalArgumentException("User not found: no-such-id"));

        mockMvc.perform(get("/api/users/no-such-id"))
                .andExpect(status().isNotFound());
    }
}
