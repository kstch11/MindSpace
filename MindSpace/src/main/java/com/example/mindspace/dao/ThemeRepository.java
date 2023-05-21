package com.example.mindspace.dao;

import com.example.mindspace.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface ThemeRepository extends JpaRepository<Theme, Integer> {
}
