package org.example.dashboard.controller;

import lombok.RequiredArgsConstructor;
import org.example.core.model.AuthRequest;
import org.example.core.model.AuthResponse;
import org.example.dashboard.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> auth(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.auth(authRequest));
    }
}
