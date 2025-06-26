package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.JwtService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
        return ResponseEntity.status(201).body(this.userService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        String token = jwtService.generateToken(user.getUsername());
        return ResponseEntity.ok(token);
//        ResponseCookie cookie = ResponseCookie.from("token", token)
//                .httpOnly(true)
//                .secure(true) // must be true in production (HTTPS)
//                .path("/")
//                .maxAge(60 * 60) // 1 hour
//                .sameSite("Strict")
//                .build();
//
//        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
//
//        return ResponseEntity.ok("Login successful");
    }
}
