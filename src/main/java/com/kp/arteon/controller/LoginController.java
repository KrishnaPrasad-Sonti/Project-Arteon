package com.kp.arteon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kp.arteon.models.Users;

import com.kp.arteon.services.LoginService;

@RestController
@RequestMapping("/Arteon")
public class LoginController {
    
    @Autowired
    private LoginService loginservice;

    // DTO for login request body
    public static class LoginRequest {
        private String email;
        private String password;

        // Getters and setters
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Extract email and password from the request body
       Users  user = loginservice.AuthenticateUser(loginRequest.getEmail(), loginRequest.getPassword());

        if (user != null) {
            return ResponseEntity.ok().body(user);
        }

        return ResponseEntity.status(401).body("incorrect_credentials");
    }
}
