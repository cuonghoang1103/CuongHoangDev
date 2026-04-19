package dev.cuonghoang.config.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * OAuth2 Configuration Properties
 * Cấu hình thuộc tính OAuth2
 * 
 * @author CuongHoang
 * @since 1.0.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "app.oauth2")
public class OAuth2Config {
    
    /**
     * OAuth2 Success Redirect URL
     */
    private String successRedirectUrl = "/?oauth2=success";
    
    /**
     * OAuth2 Failure Redirect URL
     */
    private String failureRedirectUrl = "/auth/login?error=oauth2_failed";
    
    /**
     * Authorized Redirect URIs
     */
    private String[] authorizedRedirectUris = {
        "http://localhost:3000/oauth2/redirect",
        "http://localhost:8080/oauth2/redirect",
        "https://cuonghoang.dev/oauth2/redirect"
    };
    
    /**
     * OAuth2 Cookie Name for storing redirect URI
     */
    private String redirectUriParamCookieName = "redirect_uri";
    
    /**
     * OAuth2 Cookie Expire Seconds
     */
    private int cookieExpireSeconds = 180;
}
