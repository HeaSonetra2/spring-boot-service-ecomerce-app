package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.HomeAggregateDTO;
import com.example.demo.service.HomeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/home")
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @GetMapping
    public ResponseEntity<ApiResponse<HomeAggregateDTO>> getHome(Authentication authentication) {

        String phone = authentication != null ? authentication.getName() : null;

        HomeAggregateDTO dto = homeService.getHomeData(phone);

        return ResponseEntity.ok(
                ApiResponse.<HomeAggregateDTO>builder()
                        .success(true)
                        .message("Home data loaded")
                        .data(dto)
                        .build());
    }
}


