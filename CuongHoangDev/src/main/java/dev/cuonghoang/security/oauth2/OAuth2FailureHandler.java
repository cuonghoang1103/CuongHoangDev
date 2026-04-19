package dev.cuonghoang.security.oauth2;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * OAuth2 Failure Handler
 * Handler xử lý OAuth2 authentication failure
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Component
public class OAuth2FailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {

        String targetUrl = determineFailureUrl(request, exception);

        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    private String determineFailureUrl(HttpServletRequest request, AuthenticationException exception) {
        String errorMessage = "OAuth2 authentication failed";
        if (exception.getMessage() != null) {
            errorMessage = exception.getMessage();
        }
        String baseUrl = request.getServletContext().getContextPath();
        return UriComponentsBuilder.fromUriString(baseUrl + "/auth/login")
                .queryParam("error", "oauth2")
                .queryParam("message", errorMessage)
                .build().toUriString();
    }
}
