package com.example.mindspace.dao;

import com.example.mindspace.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
}
