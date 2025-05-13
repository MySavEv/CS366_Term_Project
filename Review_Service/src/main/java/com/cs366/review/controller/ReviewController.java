package com.cs366.review.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cs366.review.dto.ReviewRequest;
import com.cs366.review.dto.ReviewResponse;
import com.cs366.review.service.ReviewService;

@RestController
@RequestMapping("/order")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/review")
    public ResponseEntity<ReviewResponse> rateRider(@RequestBody ReviewRequest request) {
        return ResponseEntity.ok(reviewService.rateRider(request));
    }

}