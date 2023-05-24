package com.example.mindspace.service.impl;

import com.example.mindspace.repository.ReviewRepository;
import com.example.mindspace.model.Client;
import com.example.mindspace.model.Review;
import com.example.mindspace.model.Therapist;
import com.example.mindspace.service.interfaces.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    @Override
    public void createReview(Client author, Therapist recipient, String text) throws Exception {
        Review review = new Review();
        if (author == null) {
            throw new Exception("Review cannot be created without an author");
        }
        if (recipient == null){
            throw new Exception("nu to zhe samoe tolko naoborot");
        }
        if (text == null) {
            throw new Exception("nelzya bez texta");
        }
        review.setAuthor(author);
        review.setRecipient(recipient);
        review.setText(text);
        reviewRepository.save(review);
    }

    @Override
    public List<Review> findAllReviewsByRecipient(Therapist therapist) {
        return reviewRepository.findReviewsByRecipient(therapist);
    }
}
