package com.example.mindspace.repository;

import com.example.mindspace.model.TimeCell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TimeCellRepository extends JpaRepository<TimeCell, Integer> {
    List<TimeCell> findByEndTimeBefore(LocalDateTime dateTime);
}
