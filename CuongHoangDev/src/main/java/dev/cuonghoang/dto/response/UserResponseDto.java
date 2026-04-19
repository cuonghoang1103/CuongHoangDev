package dev.cuonghoang.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * User Response DTO
 * DTO cho response thông tin người dùng
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Data
public class UserResponseDto {

    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String fullName;
    private String phoneNumber;
    private String bio;
    private String website;
    private String location;
    private String avatarUrl;
    private Boolean enabled;
    private Boolean emailVerified;
    private List<String> roles;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLoginAt;
    
    // Profile specific fields
    private String jobTitle;
    private String company;
    private String skills;
    private String experience;
    private String education;
    private String socialLinks;
}
