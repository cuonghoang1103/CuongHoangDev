package dev.cuonghoang.security.oauth2;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import dev.cuonghoang.entity.user.Role;
import dev.cuonghoang.entity.user.User;
import dev.cuonghoang.entity.user.UserProfile;
import dev.cuonghoang.repository.user.RoleRepository;
import dev.cuonghoang.repository.user.UserRepository;
import dev.cuonghoang.security.user.UserPrincipal;

/**
 * OAuth2 User Service
 * Service xử lý OAuth2 user information
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Service
public class OAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(
                oAuth2UserRequest.getClientRegistration().getRegistrationId(),
                oAuth2User.getAttributes());

        if (!StringUtils.hasText(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationException("Email not found from OAuth2 provider");
        }

        Optional<User> userOptional = userRepository.findByEmail(oAuth2UserInfo.getEmail());
        User user;

        if (userOptional.isPresent()) {
            user = userOptional.get();
            user = updateExistingUser(user, oAuth2UserInfo);
        } else {
            user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
        }

        return UserPrincipal.create(user, oAuth2User.getAttributes());
    }

    private User registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        User user = new User();

        user.setProvider(oAuth2UserRequest.getClientRegistration().getRegistrationId());
        user.setProviderId(oAuth2UserInfo.getId());
        user.setUsername(oAuth2UserInfo.getEmail());
        user.setEmail(oAuth2UserInfo.getEmail());
        user.setEnabled(true);
        user.setEmailVerified(true);

        // Create UserProfile with OAuth2 info
        UserProfile profile = new UserProfile();
        profile.setUser(user);
        profile.setFirstName(oAuth2UserInfo.getFirstName());
        profile.setLastName(oAuth2UserInfo.getLastName());
        profile.setAvatarUrl(oAuth2UserInfo.getImageUrl());
        user.setProfile(profile);

        // Assign default role
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("User Role not set."));
        user.setRoles(Set.of(userRole));

        return userRepository.save(user);
    }

    private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        // Update UserProfile with latest OAuth2 info
        UserProfile profile = existingUser.getProfile();
        if (profile == null) {
            profile = new UserProfile();
            profile.setUser(existingUser);
            existingUser.setProfile(profile);
        }

        profile.setFirstName(oAuth2UserInfo.getFirstName());
        profile.setLastName(oAuth2UserInfo.getLastName());
        profile.setAvatarUrl(oAuth2UserInfo.getImageUrl());

        return userRepository.save(existingUser);
    }
}
