package dev.cuonghoang.security.oauth2;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import dev.cuonghoang.security.jwt.JwtTokenProvider;
import dev.cuonghoang.security.user.UserPrincipal;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * OAuth2 Success Handler
 * Handler xử lý OAuth2 authentication success
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Value("${app.oauth2.success-redirect-url:/oauth2/redirect}")
    private String successRedirectUrl;

    @Value("${app.oauth2.failure-redirect-url:/auth/login?error=oauth2_failed}")
    private String failureRedirectUrl;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        String targetUrl = determineTargetUrl(request, response, authentication);

        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }

        clearAuthenticationAttributes(request);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) {

        String targetUrl = (successRedirectUrl != null && !successRedirectUrl.isBlank())
                ? successRedirectUrl
                : getCustomDefaultTargetUrl();

        // Generate JWT token
        String token = tokenProvider.generateToken(authentication);

        // Get user info
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        return UriComponentsBuilder.fromUriString(targetUrl)
                .queryParam("token", token)
                .queryParam("user", userPrincipal.getUsername())
                .build().toUriString();
    }

    protected String getCustomDefaultTargetUrl() {
        return "/oauth2/redirect";
    }

}
