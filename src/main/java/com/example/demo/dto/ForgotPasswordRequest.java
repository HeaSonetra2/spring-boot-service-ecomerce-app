package com.example.demo.dto;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ForgotPasswordRequest {
    private String phoneNumber;
    private String otp;
    private String newPassword;
    private String confirmPassword;
}

