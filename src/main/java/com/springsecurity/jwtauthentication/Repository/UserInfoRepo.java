package com.springsecurity.jwtauthentication.Repository;

import com.springsecurity.jwtauthentication.Entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepo extends JpaRepository<UserInfo, Integer> {

    Optional<UserInfo> findByEmail(String email);

    boolean existsByEmail(String email);
}
