package com.springsecurity.JwtAuthentication.Repository;

import com.springsecurity.JwtAuthentication.Entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepo extends JpaRepository<UserInfo, Integer> {

    Optional<UserInfo> findByEmail(String email);
}
