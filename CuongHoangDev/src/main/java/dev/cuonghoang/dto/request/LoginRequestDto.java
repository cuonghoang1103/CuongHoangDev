package dev.cuonghoang.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Login Request DTO
 * DTO cho request đăng nhập
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Data
public class LoginRequestDto {

    @NotBlank(message = "Username or email is required")
    private String usernameOrEmail;

    @NotBlank(message = "Password is required")
    private String password;

    private Boolean rememberMe = false;
}
