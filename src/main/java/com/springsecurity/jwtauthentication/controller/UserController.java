package com.springsecurity.jwtauthentication.controller;

import com.springsecurity.jwtauthentication.Entity.UserInfo;
import com.springsecurity.jwtauthentication.Service.JwtService;
import com.springsecurity.jwtauthentication.Service.UserInfoService;
import com.springsecurity.jwtauthentication.dto.AuthRequestDTO;
import com.springsecurity.jwtauthentication.dto.AuthResponseDTO;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserInfoService userInfoService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserController(UserInfoService userInfoService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userInfoService = userInfoService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome() {
        log.info("public endpoint welcome is called...");
        return ResponseEntity.ok("Welcome this endpoint is not secure");
    }

    @PostMapping("/addNewUser")
    public ResponseEntity<String> addNewUser(@Valid @RequestBody UserInfo userInfo) {
        log.info("New user registration requested for username={}", userInfo.getEmail());
        UserInfo saved = userInfoService.addUser(userInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created with email " + saved.getEmail());
    }

    @GetMapping("/test/rateLimiter")
    public String testRateLimit() {
        return "Request successful";
    }

    @PostMapping("/generateToken")
    public ResponseEntity<AuthResponseDTO> authenticateAndGenerateToken(@Valid @RequestBody AuthRequestDTO authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
                        authRequest.getPassword())
        );
        String token = jwtService.generateToken(authentication.getName());

        return ResponseEntity.ok(new AuthResponseDTO(token));
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<String> getUser() {

        return ResponseEntity.ok("Welcome, admin!");
    }

    @GetMapping("/home")
    public String getHome() {
        return "home";
    }
}
