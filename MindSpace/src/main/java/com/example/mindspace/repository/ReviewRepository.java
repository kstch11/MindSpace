package com.example.mindspace.repository;

import com.example.mindspace.model.Review;
import com.example.mindspace.model.Therapist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findReviewsByRecipient(Therapist therapist);
}
