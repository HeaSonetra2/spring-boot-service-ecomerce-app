package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.FeedPost;

public interface FeedPostRepository extends JpaRepository<FeedPost, Long> {

    List<FeedPost> findTop20ByOrderByTimestampDesc();
}


