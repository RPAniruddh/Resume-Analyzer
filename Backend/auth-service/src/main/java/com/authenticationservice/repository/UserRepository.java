package com.authenticationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.authenticationservice.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);
    boolean existsByEmail(String email);
}
