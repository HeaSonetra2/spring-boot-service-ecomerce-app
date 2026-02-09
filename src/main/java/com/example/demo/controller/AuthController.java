package com.example.demo.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.ForgotPasswordRequest;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.VerifyOtpRequest;
import com.example.demo.service.AuthService;
import com.example.demo.service.OtpService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final OtpService otpService;

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(
            @RequestBody LoginRequest request) {

        AuthResponse response = authService.login(request);

        return ResponseEntity.ok(
                ApiResponse.<AuthResponse>builder()
                        .success(true)
                        .message("Login successful")
                        .data(response)
                        .build());
    }

    // SEND REGISTER OTP
    @PostMapping("/register/send-otp")
    public ResponseEntity<ApiResponse<String>> sendRegisterOtp(
            @RequestBody VerifyOtpRequest request) {

        String otp = otpService.sendOtp(request.getPhoneNumber());

        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message("OTP sent successfully")
                        .data(otp)
                        .build());
    }

    // VERIFY REGISTER OTP
    @PostMapping("/register/verify-otp")
    public ResponseEntity<ApiResponse<Void>> verifyRegisterOtp(
            @RequestBody VerifyOtpRequest request) {

        otpService.validateOtp(request.getPhoneNumber(), request.getOtp());

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("OTP verified successfully")
                        .build());
    }

    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Map<String, String>>> register(
            @RequestBody RegisterRequest request) {

        authService.register(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<Map<String, String>>builder()
                        .success(true)
                        .message("User registered successfully")
                        .data(Map.of(
                                "phoneNumber", request.getPhoneNumber()))
                        .build());
    }

    // SEND FORGOT PASSWORD OTP
    @PostMapping("/forgot-password/send-otp")
    public ResponseEntity<ApiResponse<String>> sendForgotOtp(
            @RequestBody VerifyOtpRequest request) {

        String otp = otpService.sendOtp(request.getPhoneNumber());

        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message("OTP sent successfully")
                        .data(otp)
                        .build());
    }

    // FORGOT PASSWORD
    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse<Void>> forgotPassword(
            @RequestBody ForgotPasswordRequest request) {

        authService.forgotPassword(request);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Password reset successfully")
                        .build());
    }
}
