package com.cs366.review.dto;

import lombok.Data;

@Data
public class ReviewRequest {
    private String orderId;
    private int rate;
    private String comment;
}