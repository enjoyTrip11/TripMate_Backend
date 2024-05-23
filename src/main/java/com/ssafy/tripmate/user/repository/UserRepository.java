package com.ssafy.tripmate.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.tripmate.user.dto.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findById(String id);

    Optional<User> findByIdAndPassword(String id,String password);

    Optional<User> findByName(String name);
}