package com.example.mindspace.dao;

import com.example.mindspace.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
