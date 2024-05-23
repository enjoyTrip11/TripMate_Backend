package com.ssafy.tripmate.user.repository;

import com.ssafy.tripmate.user.dto.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Integer> {
    Optional<RefreshToken> findByTokenKey(String tokenKey);
}
