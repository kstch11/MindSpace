package com.example.mindspace.dao;

import com.example.mindspace.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
