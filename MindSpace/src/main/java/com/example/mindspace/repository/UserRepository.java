package com.example.mindspace.repository;

import com.example.mindspace.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
//    User findByUsername(String username);
}
