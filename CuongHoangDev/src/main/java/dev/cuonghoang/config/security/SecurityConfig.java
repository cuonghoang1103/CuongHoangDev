package dev.cuonghoang.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import dev.cuonghoang.security.jwt.JwtAuthenticationEntryPoint;
import dev.cuonghoang.security.jwt.JwtAuthenticationFilter;
import dev.cuonghoang.security.oauth2.OAuth2FailureHandler;
import dev.cuonghoang.security.oauth2.OAuth2SuccessHandler;
import dev.cuonghoang.security.oauth2.OAuth2UserService;

/**
 * Main Security Configuration
 * Cấu hình bảo mật chính cho ứng dụng
 *
 * @author CuongHoang
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Autowired
        private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

        @Autowired
        private JwtAuthenticationFilter jwtAuthenticationFilter;

        @Autowired
        private OAuth2UserService oAuth2UserService;

        @Autowired
        private OAuth2SuccessHandler oAuth2SuccessHandler;

        @Autowired
        private OAuth2FailureHandler oAuth2FailureHandler;

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(csrf -> csrf.disable())
                                // Cho phép frame cho H2 console (dev)
                                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
                                // Dùng session khi cần cho flow web (formLogin/OAuth2), JWT cho API
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                                .cors(org.springframework.security.config.Customizer.withDefaults())
                                .exceptionHandling(ex -> ex
                                                .authenticationEntryPoint(jwtAuthenticationEntryPoint))
                                .authorizeHttpRequests(auth -> auth
                                                // Public endpoints
                                                .requestMatchers("/", "/home", "/about/**", "/portfolio/**",
                                                                "/blog/**", "/shop/**", "/explore/**", "/game/**",
                                                                "/contact/**", "/auth/**", "/h2-console/**",
                                                                "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html",
                                                                "/search/**", "/error/**", "/favicon.ico")
                                                .permitAll()
                                                // Static resources
                                                .requestMatchers("/static/**", "/css/**", "/js/**", "/img/**",
                                                                "/fonts/**",
                                                                "/assets/**", "/uploads/**")
                                                .permitAll()
                                                // API endpoints
                                                .requestMatchers("/api/public/**").permitAll()
                                                .requestMatchers("/api/auth/**").permitAll()
                                                // Admin endpoints
                                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                                // API endpoints requiring authentication
                                                .requestMatchers("/api/**").authenticated()
                                                // All other requests require authentication
                                                .anyRequest().authenticated())
                                .oauth2Login(oauth2 -> oauth2
                                                .loginPage("/auth/login")
                                                .userInfoEndpoint(userInfo -> userInfo.userService(oAuth2UserService))
                                                .successHandler(oAuth2SuccessHandler)
                                                .failureHandler(oAuth2FailureHandler))
                                .formLogin(form -> form
                                                .loginPage("/auth/login")
                                                .loginProcessingUrl("/auth/login")
                                                .defaultSuccessUrl("/", true)
                                                .failureUrl("/auth/login?error=true")
                                                .permitAll())
                                .logout(logout -> logout
                                                .logoutUrl("/auth/logout")
                                                .logoutSuccessUrl("/")
                                                .invalidateHttpSession(true)
                                                .deleteCookies("JSESSIONID")
                                                .permitAll());

                // Thêm JWT filter trước UsernamePasswordAuthenticationFilter để xử lý API
                // Bearer token
                http.addFilterBefore(jwtAuthenticationFilter,
                                org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }

        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowCredentials(true);
                configuration.setAllowedOrigins(java.util.List.of(
                                "http://localhost:3000",
                                "http://localhost:8080",
                                "https://cuonghoang.dev"));
                configuration.setAllowedMethods(java.util.List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                configuration.setAllowedHeaders(java.util.List.of("Authorization", "Content-Type", "X-Requested-With"));
                configuration.setMaxAge(java.time.Duration.ofHours(1));
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);
                return source;
        }
}
