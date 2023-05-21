package com.example.mindspace.service.interfaces;

import com.example.mindspace.model.Client;
import com.example.mindspace.model.Review;
import com.example.mindspace.model.Therapist;

import java.util.List;

public interface ReviewService {
    public void createReview(Client author, Therapist recipient, String text) throws Exception;

    public List<Review> findAllReviewsByRecipient(Therapist therapist);
}
