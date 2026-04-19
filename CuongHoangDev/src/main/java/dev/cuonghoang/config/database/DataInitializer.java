package dev.cuonghoang.config.database;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring6.SpringTemplateEngine;

import dev.cuonghoang.entity.user.Role;
import dev.cuonghoang.entity.user.User;
import dev.cuonghoang.entity.user.UserProfile;
import dev.cuonghoang.repository.user.RoleRepository;
import dev.cuonghoang.repository.user.UserRepository;
import dev.cuonghoang.util.constants.AppConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Data Initializer
 * Khởi tạo dữ liệu ban đầu cho ứng dụng
 * 
 * @author CuongHoang
 * @since 1.0.0
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final SpringTemplateEngine templateEngine;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.admin.default-username:admin}")
    private String adminUsername;

    @Value("${app.admin.default-password:admin123}")
    private String adminPassword;

    @Value("${app.admin.default-email:admin@cuonghoang.dev}")
    private String adminEmail;

    DataInitializer(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Starting data initialization...");

        initializeRoles();
        initializeAdminUser();

        log.info("Data initialization completed.");
    }

    /**
     * Initialize default roles
     */
    private void initializeRoles() {
        log.info("Initializing roles...");

        // Create ADMIN role if not exists
        if (!roleRepository.existsByName(AppConstants.ROLE_ADMIN)) {
            Role adminRole = Role.builder()
                    .name(AppConstants.ROLE_ADMIN)
                    .description("Administrator role with full access")
                    .build();
            roleRepository.save(adminRole);
            log.info("Created ADMIN role");
        }

        // Create USER role if not exists
        if (!roleRepository.existsByName(AppConstants.ROLE_USER)) {
            Role userRole = Role.builder()
                    .name(AppConstants.ROLE_USER)
                    .description("Regular user role")
                    .build();
            roleRepository.save(userRole);
            log.info("Created USER role");
        }

        // Create MODERATOR role if not exists
        if (!roleRepository.existsByName(AppConstants.ROLE_MODERATOR)) {
            Role moderatorRole = Role.builder()
                    .name(AppConstants.ROLE_MODERATOR)
                    .description("Moderator role with limited admin access")
                    .build();
            roleRepository.save(moderatorRole);
            log.info("Created MODERATOR role");
        }

    /**
     * Initialize default admin user
     */
    private void initializeAdminUser() {
        log.info("Initializing admin user...");

        if (!userRepository.existsByEmail(adminEmail)) {
            Role adminRole = roleRepository.findByName(AppConstants.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Admin role not found"));

            // Create admin user profile
            UserProfile adminProfile = UserProfile.builder()
                    .firstName("Cuong")
                    .lastName("Hoang")
                    .displayName("Cuong Hoang")
                    .bio("System Administrator")
                    .website("https://cuonghoang.dev")
                    .location("Vietnam")
                    .build();

            // Create admin user
            User adminUser = User.builder()
                    .username(adminUsername)
                    .email(adminEmail)
                    .password(passwordEncoder.encode(adminPassword))
                    .emailVerified(true)
                    .enabled(true)
                    .accountNonExpired(true)
                    .accountNonLocked(true)
                    .credentialsNonExpired(true)
                    .roles(Set.of(adminRole))
                    .profile(adminProfile)
                    .lastLoginAt(LocalDateTime.now())
                    .build();

            // Set bidirectional relationship
            adminProfile.setUser(adminUser);

            userRepository.save(adminUser);
            log.info("Created admin user with email: {}", adminEmail);
        } else {
            log.info("Admin user already exists with email: {}", adminEmail);
        }
    }
}
