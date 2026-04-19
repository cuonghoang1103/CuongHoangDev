package dev.cuonghoang.entity.user;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import dev.cuonghoang.entity.base.AuditableEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * User Entity
 * Entity đại diện cho người dùng
 * 
 * @author CuongHoang
 * @since 1.0.0
 */
@Entity
@Table(name = "users", indexes = {
        @Index(name = "idx_user_email", columnList = "email", unique = true),
        @Index(name = "idx_user_username", columnList = "username", unique = true),
        @Index(name = "idx_user_enabled", columnList = "enabled"),
        @Index(name = "idx_user_email_verified", columnList = "email_verified")
})
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = { "roles", "profile" })
@ToString(exclude = { "roles", "profile", "password" })
public class User extends AuditableEntity {

    /**
     * Username (unique)
     */
    @Column(name = "username", unique = true, length = 50)
    private String username;

    /**
     * Email address (unique, required)
     */
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    /**
     * Password (hashed)
     */
    @Column(name = "password", length = 255)
    private String password;

    /**
     * Whether email is verified
     */
    @Column(name = "email_verified", nullable = false)
    @lombok.Builder.Default
    private Boolean emailVerified = false;

    /**
     * Email verification token
     */
    @Column(name = "email_verification_token", length = 255)
    private String emailVerificationToken;

    /**
     * Email verification token expiry
     */
    @Column(name = "email_verification_expires_at")
    private LocalDateTime emailVerificationExpiresAt;

    /**
     * Password reset token
     */
    @Column(name = "password_reset_token", length = 255)
    private String passwordResetToken;

    /**
     * Password reset token expiry
     */
    @Column(name = "password_reset_expires_at")
    private LocalDateTime passwordResetExpiresAt;

    /**
     * Whether account is enabled
     */
    @Column(name = "enabled", nullable = false)
    @lombok.Builder.Default
    private Boolean enabled = true;

    /**
     * Whether account is non-expired
     */
    @Column(name = "account_non_expired", nullable = false)
    @lombok.Builder.Default
    private Boolean accountNonExpired = true;

    /**
     * Whether account is non-locked
     */
    @Column(name = "account_non_locked", nullable = false)
    @lombok.Builder.Default
    private Boolean accountNonLocked = true;

    /**
     * Whether credentials are non-expired
     */
    @Column(name = "credentials_non_expired", nullable = false)
    @lombok.Builder.Default
    private Boolean credentialsNonExpired = true;

    /**
     * OAuth2 provider (google, github, etc.)
     */
    @Column(name = "provider", length = 50)
    private String provider;

    /**
     * OAuth2 provider ID
     */
    @Column(name = "provider_id", length = 100)
    private String providerId;

    /**
     * Last login timestamp
     */
    @Column(name = "last_login_at")
    private LocalDateTime lastLoginAt;

    /**
     * Login count
     */
    @Column(name = "login_count")
    @lombok.Builder.Default
    private Long loginCount = 0L;

    /**
     * Failed login attempts
     */
    @Column(name = "failed_login_attempts")
    @lombok.Builder.Default
    private Integer failedLoginAttempts = 0;

    /**
     * Account locked until
     */
    @Column(name = "locked_until")
    private LocalDateTime lockedUntil;

    /**
     * User roles
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"), indexes = {
            @Index(name = "idx_user_roles_user_id", columnList = "user_id"),
            @Index(name = "idx_user_roles_role_id", columnList = "role_id")
    })
    @lombok.Builder.Default
    private Set<Role> roles = new HashSet<>();

    /**
     * User profile (one-to-one relationship)
     */
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserProfile profile;

    /**
     * Add role to user
     */
    public void addRole(Role role) {
        roles.add(role);
        role.getUsers().add(this);
    }

    /**
     * Remove role from user
     */
    public void removeRole(Role role) {
        roles.remove(role);
        role.getUsers().remove(this);
    }

    /**
     * Check if user has specific role
     */
    public boolean hasRole(String roleName) {
        return roles.stream()
                .anyMatch(role -> role.getName().equals(roleName));
    }

    /**
     * Check if user is admin
     */
    @Transient
    public boolean isAdmin() {
        return hasRole("ROLE_ADMIN") || hasRole("ADMIN");
    }

    /**
     * Check if user is moderator
     */
    @Transient
    public boolean isModerator() {
        return hasRole("ROLE_MODERATOR") || hasRole("MODERATOR");
    }

    /**
     * Get display name
     */
    @Transient
    public String getDisplayName() {
        if (profile != null && profile.getDisplayName() != null) {
            return profile.getDisplayName();
        }
        if (profile != null && profile.getFirstName() != null && profile.getLastName() != null) {
            return profile.getFirstName() + " " + profile.getLastName();
        }
        if (username != null) {
            return username;
        }
        return email;
    }

    /**
     * Get full name
     */
    @Transient
    public String getFullName() {
        if (profile != null && profile.getFirstName() != null && profile.getLastName() != null) {
            return profile.getFirstName() + " " + profile.getLastName();
        }
        return getDisplayName();
    }

    /**
     * Check if account is locked
     */
    @Transient
    public boolean isAccountLocked() {
        return lockedUntil != null && lockedUntil.isAfter(LocalDateTime.now());
    }

    /**
     * Lock account for specified minutes
     */
    public void lockAccount(int minutes) {
        this.lockedUntil = LocalDateTime.now().plusMinutes(minutes);
        this.accountNonLocked = false;
    }

    /**
     * Unlock account
     */
    public void unlockAccount() {
        this.lockedUntil = null;
        this.accountNonLocked = true;
        this.failedLoginAttempts = 0;
    }

    /**
     * Increment failed login attempts
     */
    public void incrementFailedLoginAttempts() {
        this.failedLoginAttempts++;
    }

    /**
     * Reset failed login attempts
     */
    public void resetFailedLoginAttempts() {
        this.failedLoginAttempts = 0;
    }

    /**
     * Update last login
     */
    public void updateLastLogin() {
        this.lastLoginAt = LocalDateTime.now();
        this.loginCount++;
        resetFailedLoginAttempts();
    }
}
