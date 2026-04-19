package dev.cuonghoang.security.oauth2;

import java.util.Map;

/**
 * OAuth2 User Info Abstract Class
 * Abstract class for OAuth2 user information
 * 
 * @author CuongHoang
 * @version 1.0
 */
public abstract class OAuth2UserInfo {
    
    protected Map<String, Object> attributes;

    public OAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public abstract String getId();

    public abstract String getFirstName();

    public abstract String getLastName();

    public abstract String getEmail();

    public abstract String getImageUrl();
}
