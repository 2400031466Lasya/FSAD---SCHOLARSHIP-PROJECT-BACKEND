package com.klu.scholarship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.klu.scholarship.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email); // ✅ IMPORTANT

}