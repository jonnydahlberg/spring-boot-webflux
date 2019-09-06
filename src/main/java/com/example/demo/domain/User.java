package com.example.demo.domain;


import lombok.Value;

import javax.validation.constraints.Email;
import java.util.UUID;

@Value
public class User {

    UUID id;

    @Email
    String email;
}
