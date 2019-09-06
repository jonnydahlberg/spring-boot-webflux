package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.exception.UserAlreadyExists;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

@Component
public class UserService {

    private Map<UUID, User> users = new HashMap<>();

    public Mono<User> create(String email) {

        if (userAlreadyExists(email, users.values().stream())) {
            return Mono.error(new UserAlreadyExists("Email already exists"));
        }

        var user = new User(UUID.randomUUID(), email);
        users.put(user.getId(), user);
        return Mono.just(user);
    }

    public Mono<User> get(UUID id) {

        if (!users.containsKey(id)) {
            return Mono.empty();
        }

        return Mono.just(users.get(id));
    }

    public Flux<User> getAll() {
        return Flux.fromIterable(users.values());
    }


    private boolean userAlreadyExists(String email, Stream<User> users) {
        return users.anyMatch(user -> user.getEmail().equals(email));
    }

}
