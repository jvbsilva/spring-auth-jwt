package com.joaovictor.userauthdemo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping("/")
    ResponseEntity home(){
        System.out.println("Entrou na home.");
        return ResponseEntity.ok().build();
    }
}
