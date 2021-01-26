package com.example.cassandrademo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class Controller {
    private final Repository repository;

    @GetMapping(value = "/test")
    public Map<String, String> getCount() {
        return Map.of("count", repository.getCount().toString());
    }
}
