package com.example.demo.config;

import com.example.demo.exception.UserAlreadyExists;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;

@Component
public class ErrorAttributes extends DefaultErrorAttributes {


    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(request, includeStackTrace);
        Throwable error = getError(request);


        if (error instanceof UserAlreadyExists) {
            UserAlreadyExists userAlreadyExists = (UserAlreadyExists) error;
            map.put("status", userAlreadyExists.getStatus().value());
            map.put("message", error.getMessage());
            map.put("error", userAlreadyExists.getStatus().getReasonPhrase());
        }

        return map;

    }
}
