package com.heilash.logistoffer.persistence.repository;

import com.heilash.logistoffer.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String name);
}