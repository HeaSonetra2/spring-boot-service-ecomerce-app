package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FeedDTO {

    private Long id;
    private String name;
    private Integer qty;
    private String type;
    private Double price;
    private String desc;
    private boolean isFav;
    private Double nutrition;
    private Integer review;
    private String imageUrl;
}


