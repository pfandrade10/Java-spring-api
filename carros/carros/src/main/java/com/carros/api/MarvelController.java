package com.carros.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/marvel")
public class MarvelController {

    @GetMapping()
    public ResponseEntity get(){
        return ResponseEntity.ok("Hello!");
    }
}
