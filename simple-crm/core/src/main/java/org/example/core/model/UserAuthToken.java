package org.example.core.model;

import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
public class UserAuthToken extends UsernamePasswordAuthenticationToken {
    private final Long accountId;
    public UserAuthToken(Object principal, Object credentials, Long accountId) {
        super(principal, credentials);
        this.accountId = accountId;
    }
}
