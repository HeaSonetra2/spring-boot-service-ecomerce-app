package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.example.demo.entity.Otp;

import jakarta.transaction.Transactional;

public interface OtpRepository extends JpaRepository<Otp, Long> {

    Optional<Otp> findTopByPhoneNumberOrderByExpiryTimeDesc(String phoneNumber);

    @Modifying
    @Transactional
    void deleteByPhoneNumber(String phoneNumber);
}

