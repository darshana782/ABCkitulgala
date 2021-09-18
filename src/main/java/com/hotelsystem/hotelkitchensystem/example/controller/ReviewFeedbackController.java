package com.hotelsystem.hotelkitchensystem.example.controller;

import com.hotelsystem.hotelkitchensystem.example.model.ReviewFeedback;
import com.hotelsystem.hotelkitchensystem.example.service.ReviewFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3030")
@RequestMapping("/customer/review")
@RestController
public class ReviewFeedbackController {

    @Autowired
    private ReviewFeedbackService service;

    @PostMapping("/addReviewFeedback")
    public ReviewFeedback addReviewFeedback(@RequestBody ReviewFeedback reviewFeedback){
        return service.saveReviewFeedback(reviewFeedback);
    }
    @GetMapping("/reviewFeedback")
    public List<ReviewFeedback> findAllReviewFeedback(){
        return service.getReviewFeedback();
    }
}
