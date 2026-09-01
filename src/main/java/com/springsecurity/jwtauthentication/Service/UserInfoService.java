package com.springsecurity.JwtAuthentication.Service;

import com.springsecurity.JwtAuthentication.Entity.UserInfo;
import com.springsecurity.JwtAuthentication.Repository.UserInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {
    private final UserInfoRepo userInfoRepo;
    private final PasswordEncoder passwordEncoder;

    public UserInfoService(UserInfoRepo userInfoRepo, PasswordEncoder passwordEncoder) {
        this.userInfoRepo = userInfoRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // fetch the username from db
        Optional<UserInfo> userInfo = userInfoRepo.findByEmail(username);

        if (userInfo.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email:" + username);
        }

        UserInfo user = userInfo.get();
        return new UserInfoDetails(user);
    }

    public String addUser(UserInfo userInfo) {
        // Encrypt the password
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepo.save(userInfo);
        return "User saved successfully";
    }
}
