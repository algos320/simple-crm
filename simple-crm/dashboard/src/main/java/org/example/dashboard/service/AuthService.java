package org.example.dashboard.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.core.entity.Contact;
import org.example.core.model.AuthRequest;
import org.example.core.model.AuthResponse;
import org.example.core.model.UserAuthToken;
import org.example.core.service.ContactService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final ContactService contactService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse auth(AuthRequest authRequest) {
        Contact contact = contactService
                .findContactByEmail(authRequest.getEmail());
        authenticate(authRequest, contact);

        String token = jwtService.generateToken(Collections.EMPTY_MAP, contact);
        return new AuthResponse(token);
    }

    private void authenticate(AuthRequest authRequest, Contact account) {
        try {
            authenticationManager.authenticate(
                    new UserAuthToken(
                            authRequest.getEmail(),
                            authRequest.getPassword(),
                            account.getId()
                    )
            );
        } catch (BadCredentialsException e) {
            log.error("", e);
        }
    }
}
