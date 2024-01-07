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

    /**
     * Creates a review from the given ReviewResponse.
     *
     * @param reviewDto Review details.
     * @throws EntityNotFoundException if therapist or client not found.
     * @throws Exception for other issues during review creation.
     */
    @Override
    public void createReview(ReviewResponse reviewDto) throws Exception {
        var therapist = therapistRepository.findById(reviewDto.therapistId())
                .orElseThrow(() -> new EntityNotFoundException("Therapist not found"));

        var client = clientRepository.findById(reviewDto.author())
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));


    }

    /**
     * Retrieves reviews for a given therapist.
     *
     * @param therapist The therapist to find reviews for.
     * @return List of reviews for the therapist.
     */
    @Override
    public List<Review> findAllReviewsByRecipient(Therapist therapist) {
        return reviewRepository.findReviewsByRecipient(therapist);
    }
}
