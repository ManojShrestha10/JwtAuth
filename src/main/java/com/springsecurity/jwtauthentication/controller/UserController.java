package com.springsecurity.JwtAuthentication.controller;

import com.springsecurity.JwtAuthentication.Entity.UserInfo;
import com.springsecurity.JwtAuthentication.Service.JwtService;
import com.springsecurity.JwtAuthentication.Service.UserInfoService;
import com.springsecurity.JwtAuthentication.dto.AuthRequestDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {
    private final UserInfoService userInfoService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserController(UserInfoService userInfoService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userInfoService = userInfoService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return userInfoService.addUser(userInfo);
    }

    @PostMapping("/generateToken")
    public String authenticateAndGenerateToken(@RequestBody AuthRequestDTO authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
                        authRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("Invalid user Request!");
        }
    }

}
