package com.example.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.config.JwtService;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.ForgotPasswordRequest;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.exception.ApiException;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final OtpService otpService;
    private final UserService userService;

    public AuthResponse login(LoginRequest request) {

        System.out.println("LOGIN phone = " + request.getPhoneNumber());
        System.out.println("LOGIN raw password = " + request.getPassword());

        User user = userRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow(() -> new ApiException(
                        HttpStatus.UNAUTHORIZED,
                        "User not found"));

        System.out.println("DB password = " + user.getPassword());

        boolean match = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword());

        System.out.println("PASSWORD MATCH = " + match);

        if (!match) {
            throw new ApiException(
                    HttpStatus.UNAUTHORIZED,
                    "Invalid phone number or password");
        }

        String token = jwtService.generateToken(user.getPhoneNumber());

        return new AuthResponse(token);
    }

    public void register(RegisterRequest request) {
        userService.createUser(request);
    }

    public void forgotPassword(ForgotPasswordRequest request) {
        otpService.validateOtp(request.getPhoneNumber(), request.getOtp());

        User user = userRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!request.getNewPassword().equals(request.getConfirmPassword()))
            throw new RuntimeException("Passwords do not match");

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }
}
