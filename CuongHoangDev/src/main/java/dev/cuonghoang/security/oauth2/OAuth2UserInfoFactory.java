package dev.cuonghoang.security.oauth2;

import java.util.Map;

/**
 * OAuth2 User Info Factory
 * Factory class for creating OAuth2UserInfo instances
 * 
 * @author CuongHoang
 * @version 1.0
 */
public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if (registrationId.equalsIgnoreCase("google")) {
            return new GoogleOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase("github")) {
            return new GithubOAuth2UserInfo(attributes);
        } else {
            throw new RuntimeException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}

/**
 * Google OAuth2 User Info
 */
class GoogleOAuth2UserInfo extends OAuth2UserInfo {

    public GoogleOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getFirstName() {
        return (String) attributes.get("given_name");
    }

    @Override
    public String getLastName() {
        return (String) attributes.get("family_name");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getImageUrl() {
        return (String) attributes.get("picture");
    }
}

/**
 * Github OAuth2 User Info
 */
class GithubOAuth2UserInfo extends OAuth2UserInfo {

    public GithubOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return String.valueOf(attributes.get("id"));
    }

    @Override
    public String getFirstName() {
        String name = (String) attributes.get("name");
        if (name != null && name.contains(" ")) {
            return name.split(" ")[0];
        }
        return name;
    }

    @Override
    public String getLastName() {
        String name = (String) attributes.get("name");
        if (name != null && name.contains(" ")) {
            String[] parts = name.split(" ");
            if (parts.length > 1) {
                return parts[parts.length - 1];
            }
        }
        return null;
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getImageUrl() {
        return (String) attributes.get("avatar_url");
    }
}
