package com.example.demo;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @GetMapping("/")
    public ResponseEntity<?> home() {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<?> user() {
        String rawPassword = "1234";
        String salt = BCrypt.gensalt();
        String encPassword = BCrypt.hashpw(rawPassword, salt);
        System.out.println("salt : " + salt);
        System.out.println(encPassword);
        return new ResponseEntity<>(encPassword, HttpStatus.OK);
    }

    @GetMapping("/verify2")
    public ResponseEntity<?> verify() {
        String rawPassword = "1234";
        String dbPassword = "$2a$10$JPL/lnIokpo4Wd5nSgFfs.ug.hPRuMC2MKJOdtWP.8hhzNx3euZlW";
        boolean isSame = BCrypt.checkpw(rawPassword, dbPassword);
        return new ResponseEntity<>(isSame, HttpStatus.OK);
    }
}
