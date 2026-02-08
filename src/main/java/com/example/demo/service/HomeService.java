package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.BestSellingProductDTO;
import com.example.demo.dto.FeedDTO;
import com.example.demo.dto.HomeAggregateDTO;
import com.example.demo.dto.PromotionDTO;
import com.example.demo.entity.Product;
import com.example.demo.entity.Promotion;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.PromotionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HomeService {

        private final PromotionRepository promotionRepository;
        private final ProductRepository productRepository;

        public HomeAggregateDTO getHomeData(String currentUserPhone) {

                List<PromotionDTO> promotions = promotionRepository
                                .findByExpiryDateAfterOrderByExpiryDateAsc(LocalDateTime.now())
                                .stream()
                                .map(this::toPromotionDTO)
                                .collect(Collectors.toList());

                List<FeedDTO> feed = productRepository
                                .findTop10ByOrderByRatingDesc()
                                .stream()
                                .map(this::toFeedDTO)
                                .collect(Collectors.toList());

                List<BestSellingProductDTO> bestSelling = productRepository
                                .findTop10ByOrderByRatingDesc()
                                .stream()
                                .map(this::toBestSellingProductDTO)
                                .collect(Collectors.toList());

                return HomeAggregateDTO.builder()
                                .promotions(promotions)
                                .feed(feed)
                                .bestSelling(bestSelling)
                                .build();
        }

        private PromotionDTO toPromotionDTO(Promotion promotion) {
                return PromotionDTO.builder()
                                .id(promotion.getId())
                                .imageUrl(promotion.getImageUrl())
                                .build();
        }

        private FeedDTO toFeedDTO(Product product) {
                return FeedDTO.builder()
                                .id(product.getId())
                                .name(product.getName())
                                .qty(product.getStockQuantity())
                                .type("pcs")
                                .price(product.getPrice() != null ? product.getPrice().doubleValue() : null)
                                .desc(product.getDescription())
                                .isFav(false)
                                .nutrition(product.getRating())
                                .review(product.getRating() != null ? product.getRating().intValue() : null)
                                .imageUrl(product.getThumbnailUrl())
                                .build();
        }

        private BestSellingProductDTO toBestSellingProductDTO(Product product) {
                return BestSellingProductDTO.builder()
                                .id(product.getId())
                                .name(product.getName())
                                .qty(product.getStockQuantity())
                                .type("pcs")
                                .price(product.getPrice() != null ? product.getPrice().doubleValue() : null)
                                .desc(product.getDescription())
                                .isFav(false)
                                .nutrition(product.getRating())
                                .review(product.getRating() != null ? product.getRating().intValue() : null)
                                .imageUrl(product.getThumbnailUrl())
                                .build();
        }
}
