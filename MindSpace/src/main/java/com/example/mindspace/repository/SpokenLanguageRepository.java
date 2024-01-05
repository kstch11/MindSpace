package com.example.mindspace.repository;

import com.example.mindspace.model.SpokenLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpokenLanguageRepository extends JpaRepository<SpokenLanguage, Integer> {
}
