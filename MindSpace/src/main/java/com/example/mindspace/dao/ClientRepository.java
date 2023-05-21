package com.example.mindspace.dao;

import com.example.mindspace.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
}
