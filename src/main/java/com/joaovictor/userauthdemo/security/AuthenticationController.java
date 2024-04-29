package com.joaovictor.userauthdemo.security;

import com.joaovictor.userauthdemo.domain.user.User;
import com.joaovictor.userauthdemo.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping(value ="auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    UserRepository userRepository;

    @GetMapping(value="/register")
    public ResponseEntity<String> getRegister(){
        return ResponseEntity.ok().build();
    }

    @PostMapping(value="/register")
    public ResponseEntity<String> postRegister(@RequestBody UserRegistrationDTO data){
        if(userRepository.findByEmail(data.email()).isPresent()){
            return ResponseEntity.badRequest().build();
        }
        User newUser = new User(data.name(),data.email(),data.email());
        userRepository.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(value="/login")
    public ResponseEntity<String> postLogin(Authentication authentication) {
        String token = authenticationService.authenticate(authentication);
        return ResponseEntity.ok().body(token);
    }
}
