package dev.cuonghoang.config.database;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;

/**
 * Database Configuration
 * Cấu hình cơ sở dữ liệu
 * 
 * @author CuongHoang
 * @since 1.0.0
 */
@Configuration
@EnableJpaRepositories(basePackages = "dev.cuonghoang.repository")
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@EnableTransactionManagement
public class DatabaseConfig {

    /**
     * Auditor Provider for JPA Auditing
     * Cung cấp thông tin người dùng hiện tại cho JPA Auditing
     */
    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }

    /**
     * Implementation of AuditorAware
     */
    public static class AuditorAwareImpl implements AuditorAware<String> {
        
        @Override
        public Optional<String> getCurrentAuditor() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            
            if (authentication == null || !authentication.isAuthenticated() || 
                "anonymousUser".equals(authentication.getPrincipal())) {
                return Optional.of("system");
            }
            
            // If using custom UserPrincipal
            if (authentication.getPrincipal() instanceof dev.cuonghoang.security.user.UserPrincipal) {
                dev.cuonghoang.security.user.UserPrincipal userPrincipal = 
                    (dev.cuonghoang.security.user.UserPrincipal) authentication.getPrincipal();
                return Optional.of(userPrincipal.getEmail());
            }
            
            // If using OAuth2 authentication
            if (authentication.getPrincipal() instanceof org.springframework.security.oauth2.core.user.OAuth2User) {
                org.springframework.security.oauth2.core.user.OAuth2User oauth2User = 
                    (org.springframework.security.oauth2.core.user.OAuth2User) authentication.getPrincipal();
                String email = oauth2User.getAttribute("email");
                return Optional.ofNullable(email != null ? email : oauth2User.getName());
            }
            
            // Default case
            return Optional.of(authentication.getName());
        }
    }
}
