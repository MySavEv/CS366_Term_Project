package com.cs366.review.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs366.review.dto.ReviewRequest;
import com.cs366.review.dto.ReviewResponse;
import com.cs366.review.model.Review;
import com.cs366.review.repository.ReviewRepository;

@Service
public class ReviewService {
    
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private KafkaService kafkaService;

    public ReviewResponse rateRider(ReviewRequest request) {
        Review r = new Review();
        r.setOrderId(request.getOrderId());
        r.setRate(request.getRate());
        r.setComment(request.getComment());
        reviewRepository.save(r);


        kafkaService.sendMessage("ReviewSubmitted", "ReviewSubmitted");

        return ReviewResponse.builder()
                .status("success")
                .message("Rated Rider Successfully")
                .build();
    }

}
