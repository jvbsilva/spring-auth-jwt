package com.joaovictor.userauthdemo.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class AuthenticationService {
    public String authenticate(Authentication authentication){
        return "token";
    }
}
