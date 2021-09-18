package com.hotelsystem.hotelkitchensystem.example.service;

import com.hotelsystem.hotelkitchensystem.example.model.ReviewFeedback;
import com.hotelsystem.hotelkitchensystem.example.repository.ReviewFeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewFeedbackService {

    @Autowired
    private ReviewFeedbackRepository repository;

    public ReviewFeedback saveReviewFeedback(ReviewFeedback reviewFeedback){
       return repository.save(reviewFeedback);
    }

    public List<ReviewFeedback> getReviewFeedback(){
        return repository.findAll();
    }

}
