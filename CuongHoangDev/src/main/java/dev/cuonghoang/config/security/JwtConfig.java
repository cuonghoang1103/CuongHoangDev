package dev.cuonghoang.config.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * JWT Configuration Properties
 * Cấu hình thuộc tính JWT
 * 
 * @author CuongHoang
 * @since 1.0.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "app.jwt")
public class JwtConfig {
    
    /**
     * JWT Secret Key
     */
    private String secret = "cuonghoangdev-jwt-secret-key-2024-very-long-and-secure";
    
    /**
     * JWT Token Expiration Time (in milliseconds)
     * Default: 24 hours
     */
    private long expiration = 86400000L;
    
    /**
     * JWT Refresh Token Expiration Time (in milliseconds)
     * Default: 7 days
     */
    private long refreshExpiration = 604800000L;
    
    /**
     * JWT Token Header Name
     */
    private String header = "Authorization";
    
    /**
     * JWT Token Prefix
     */
    private String prefix = "Bearer ";
    
    /**
     * JWT Issuer
     */
    private String issuer = "CuongHoangDev";
    
    /**
     * JWT Audience
     */
    private String audience = "cuonghoang.dev";
}
