package com.example.agent.web;

import com.example.agent.domain.User;
import com.example.agent.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService users;

    public UserController(UserService users) {
        this.users = users;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User create(@RequestParam String name, @RequestParam String email) {
        return users.create(name, email);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getById(@PathVariable String id) {
        try {
            return users.getById(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
