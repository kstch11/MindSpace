package com.example.mindspace.dao;

import com.example.mindspace.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
//    User findByUsername(String username);
}
