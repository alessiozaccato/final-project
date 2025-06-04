package com.java.finalproject.final_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.finalproject.final_project.model.User;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
