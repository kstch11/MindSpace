package com.example.mindspace.repository;

import com.example.mindspace.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
