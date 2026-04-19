package dev.cuonghoang.security.jwt;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.cuonghoang.dto.response.ErrorResponseDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * JWT Authentication Entry Point
 * Entry point xử lý authentication exception
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {

        // Check if request is for API endpoint
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/api/")) {
            // Return JSON error response for API requests
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            ErrorResponseDto errorResponse = ErrorResponseDto.of(
                    "Unauthorized",
                    "Sorry, You're not authorized to access this resource.",
                    HttpServletResponse.SC_UNAUTHORIZED,
                    requestURI);

            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        } else {
            // Avoid redirect loop if the current request is already an auth page
            if (requestURI.startsWith("/auth/")) {
                response.setContentType(MediaType.TEXT_PLAIN_VALUE);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Unauthorized");
            } else {
                // Redirect to login page for web requests
                response.sendRedirect("/auth/login?error=unauthorized");
            }
        }
    }
}
