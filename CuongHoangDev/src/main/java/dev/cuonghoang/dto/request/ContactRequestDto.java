package dev.cuonghoang.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Contact Request DTO
 * DTO cho request liên hệ
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Data
public class ContactRequestDto {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    private String phoneNumber;

    @NotBlank(message = "Subject is required")
    @Size(min = 5, max = 200, message = "Subject must be between 5 and 200 characters")
    private String subject;

    @NotBlank(message = "Message is required")
    @Size(min = 10, max = 2000, message = "Message must be between 10 and 2000 characters")
    private String message;

    private String category; // GENERAL, SUPPORT, BUSINESS, COLLABORATION

    private String company;
    private String website;
    private String preferredContactMethod; // EMAIL, PHONE, BOTH
    private String urgency; // LOW, MEDIUM, HIGH
}
