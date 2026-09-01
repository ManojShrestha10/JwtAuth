package com.springsecurity.jwtauthentication.exception;

import com.springsecurity.jwtauthentication.dto.ErrorResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseDTO> handleBadCredentials(BadCredentialsException ex) {
        log.warn("Bad credentials attempt: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponseDTO("Invalid username or password"));
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ErrorResponseDTO> handleDisabledException(DisabledException e) {
        log.warn("Disabled account login attempt: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponseDTO("Account disabled"));
    }

    @ExceptionHandler(AccessDeniedException.class)
        public ResponseEntity<ErrorResponseDTO> handleForbiddenException(AccessDeniedException ae) {
        log.warn("Access Denied", ae);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponseDTO("No Permission!"));
    }
    @ExceptionHandler(UserAlreadyExistsException.class)
    public  ResponseEntity<ErrorResponseDTO> handleUserAlreadyExistsException(UserAlreadyExistsException ue) {
        log.warn("Registration failed - user already exists: {}", ue.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponseDTO(ue.getMessage()));
    }


}
