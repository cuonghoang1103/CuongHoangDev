package dev.cuonghoang.dto.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import dev.cuonghoang.dto.request.UserRequestDto;
import dev.cuonghoang.dto.response.UserResponseDto;
import dev.cuonghoang.entity.user.Role;
import dev.cuonghoang.entity.user.User;

/**
 * User Mapper
 * Mapper cho User entity và DTOs
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * Convert User entity to UserResponseDto
     */
    @Mapping(target = "fullName", expression = "java(getFullNameFromProfile(user))")
    @Mapping(target = "firstName", ignore = true)
    @Mapping(target = "lastName", ignore = true)
    @Mapping(target = "phoneNumber", ignore = true)
    @Mapping(target = "bio", ignore = true)
    @Mapping(target = "website", ignore = true)
    @Mapping(target = "location", ignore = true)
    @Mapping(target = "avatarUrl", ignore = true)
    @Mapping(target = "jobTitle", ignore = true)
    @Mapping(target = "company", ignore = true)
    @Mapping(target = "skills", ignore = true)
    @Mapping(target = "experience", ignore = true)
    @Mapping(target = "education", ignore = true)
    @Mapping(target = "socialLinks", ignore = true)
    @Mapping(target = "roles", source = "roles", qualifiedByName = "rolesToStringList")
    UserResponseDto toResponseDto(User user);

    /**
     * Convert UserRequestDto to User entity
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "enabled", constant = "true")
    @Mapping(target = "emailVerified", constant = "false")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "lastLoginAt", ignore = true)
    User toEntity(UserRequestDto userRequestDto);

    /**
     * Convert list of User entities to list of UserResponseDto
     */
    List<UserResponseDto> toResponseDtoList(List<User> users);

    /**
     * Convert Set of Roles to List of String
     */
    @Named("rolesToStringList")
    default List<String> rolesToStringList(Set<Role> roles) {
        if (roles == null) {
            return Collections.emptyList();
        }
        return roles.stream()
                .map(Role::getName)
                .toList();
    }

    /**
     * Get full name from UserProfile
     */
    default String getFullNameFromProfile(User user) {
        if (user == null || user.getProfile() == null) {
            return user != null ? user.getUsername() : "";
        }

        String firstName = user.getProfile().getFirstName();
        String lastName = user.getProfile().getLastName();

        if (firstName != null && lastName != null) {
            return firstName + " " + lastName;
        } else if (firstName != null) {
            return firstName;
        } else if (lastName != null) {
            return lastName;
        } else {
            return user.getUsername();
        }
    }
}
