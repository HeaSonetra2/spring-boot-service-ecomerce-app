package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Otp;
import com.example.demo.repository.OtpRepository;
import com.example.demo.util.OtpGenerator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OtpService {

    private final OtpRepository otpRepository;

    public void sendOtp(String phoneNumber) {
        // 🔥 important
        otpRepository.deleteByPhoneNumber(phoneNumber);

        String code = OtpGenerator.generate();

        Otp otp = new Otp();
        otp.setPhoneNumber(phoneNumber);
        otp.setCode(code);
        otp.setExpiryTime(LocalDateTime.now().plusMinutes(5));

        otpRepository.save(otp);

        System.out.println("OTP for " + phoneNumber + ": " + code);
    }

    public void validateOtp(String phoneNumber, String code) {
        Otp otp = otpRepository
                .findTopByPhoneNumberOrderByExpiryTimeDesc(phoneNumber)
                .orElseThrow(() -> new RuntimeException("OTP not found"));

        if (otp.getExpiryTime().isBefore(LocalDateTime.now()))
            throw new RuntimeException("OTP expired");

        if (!otp.getCode().equals(code))
            throw new RuntimeException("Invalid OTP");

        otpRepository.deleteByPhoneNumber(phoneNumber);
    }
}
