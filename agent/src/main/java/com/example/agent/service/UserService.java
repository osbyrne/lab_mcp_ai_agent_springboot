package com.example.agent.service;

import com.example.agent.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final List<User> USERS = List.of(
            new User(1L, "Alice"),
            new User(2L, "Bob")
    );

    public List<User> findAll() {
        return USERS;
    }

    public Optional<User> findById(Long id) {
        return USERS.stream().filter(u -> u.id().equals(id)).findFirst();
    }
}
