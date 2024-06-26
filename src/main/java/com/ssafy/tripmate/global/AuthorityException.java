package com.ssafy.tripmate.global;

import org.springframework.security.core.AuthenticationException;

public class AuthorityException extends AuthenticationException {
    private static final String MESSAGE = "권한 정보가 없는 토큰입니다.";

    public AuthorityException() {
        super(MESSAGE);
    }

    public AuthorityException(String message) {
        super(message);
    }
}
