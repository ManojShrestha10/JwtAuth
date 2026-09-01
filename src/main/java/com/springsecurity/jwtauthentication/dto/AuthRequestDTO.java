package com.springsecurity.jwtauthentication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequestDTO {
    private String username;
    @ToString.Exclude
    private String password;
}
