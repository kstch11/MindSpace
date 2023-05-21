package com.example.mindspace.dao;

import com.example.mindspace.model.Therapist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface TherapistRepository extends JpaRepository<Therapist, Integer> {
    Therapist findByName(String name);
    Therapist findBySurname(String surname);

}
