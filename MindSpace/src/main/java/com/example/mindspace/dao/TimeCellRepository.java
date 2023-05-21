package com.example.mindspace.dao;

import com.example.mindspace.model.TimeCell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface TimeCellRepository extends JpaRepository<TimeCell, Integer> {
}
