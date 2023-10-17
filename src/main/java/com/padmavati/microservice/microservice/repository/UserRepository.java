package com.padmavati.microservice.microservice.repository;

import com.padmavati.microservice.microservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    List<User> findByAge(int age);
}
