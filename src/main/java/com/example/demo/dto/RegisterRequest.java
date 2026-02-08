package com.example.demo.dto;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String phoneNumber;
    private String password;
    private String confirmPassword;
    private String name;
    private String gender;
    private LocalDate dob;
}
