package com.example.mindspace.repository;

import com.example.mindspace.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
