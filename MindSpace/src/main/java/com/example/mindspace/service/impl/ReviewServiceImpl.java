package com.example.mindspace.service.impl;

import com.example.mindspace.api.ReviewResponse;
import com.example.mindspace.exception.EntityNotFoundException;
import com.example.mindspace.repository.ClientRepository;
import com.example.mindspace.repository.ReviewRepository;
import com.example.mindspace.model.Client;
import com.example.mindspace.model.Review;
import com.example.mindspace.model.Therapist;
import com.example.mindspace.repository.TherapistRepository;
import com.example.mindspace.service.interfaces.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    private final ClientRepository clientRepository;

    private final TherapistRepository therapistRepository;

    @Override
    public void createReview(ReviewResponse reviewDto) throws Exception {
        var therapist = therapistRepository.findById(reviewDto.therapistId())
                .orElseThrow(() -> new EntityNotFoundException("Therapist not found"));

        var client = clientRepository.findById(reviewDto.author())
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));


    }

    //    @Override
//    public void createReview(Client author, Therapist recipient, String text) throws Exception {
//        Review review = new Review();
//        if (author == null) {
//            throw new Exception("Review cannot be created without an author");
//        }
//        if (recipient == null){
//            throw new Exception("nu to zhe samoe tolko naoborot");
//        }
//        if (text == null) {
//            throw new Exception("nelzya bez texta");
//        }
//        review.setAuthor(author);
//        review.setRecipient(recipient);
//        review.setText(text);
//        reviewRepository.save(review);
//    }

    @Override
    public List<Review> findAllReviewsByRecipient(Therapist therapist) {
        return reviewRepository.findReviewsByRecipient(therapist);
    }
}
