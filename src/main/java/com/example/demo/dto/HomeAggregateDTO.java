package com.example.demo.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HomeAggregateDTO {

    private List<PromotionDTO> promotions;
    private List<FeedDTO> feed;
    private List<BestSellingProductDTO> bestSelling;
}


