package dev.cuonghoang.entity.user;

import dev.cuonghoang.entity.base.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/**
 * User Profile Entity
 * Entity đại diện cho thông tin profile của người dùng
 * 
 * @author CuongHoang
 * @since 1.0.0
 */
@Entity
@Table(name = "user_profiles", indexes = {
    @Index(name = "idx_profile_user_id", columnList = "user_id", unique = true),
    @Index(name = "idx_profile_display_name", columnList = "display_name")
})
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"user"})
@ToString(exclude = {"user"})
public class UserProfile extends AuditableEntity {

    /**
     * User reference (one-to-one)
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    /**
     * First name
     */
    @Column(name = "first_name", length = 50)
    private String firstName;

    /**
     * Last name
     */
    @Column(name = "last_name", length = 50)
    private String lastName;

    /**
     * Display name (public name)
     */
    @Column(name = "display_name", length = 100)
    private String displayName;

    /**
     * Avatar image URL
     */
    @Column(name = "avatar_url", length = 500)
    private String avatarUrl;

    /**
     * Cover image URL
     */
    @Column(name = "cover_url", length = 500)
    private String coverUrl;

    /**
     * Bio/About me
     */
    @Column(name = "bio", length = 1000)
    private String bio;

    /**
     * Date of birth
     */
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    /**
     * Gender
     */
    @Column(name = "gender", length = 20)
    private String gender;

    /**
     * Phone number
     */
    @Column(name = "phone", length = 20)
    private String phone;

    /**
     * Address
     */
    @Column(name = "address", length = 255)
    private String address;

    /**
     * City
     */
    @Column(name = "city", length = 100)
    private String city;

    /**
     * Country
     */
    @Column(name = "country", length = 100)
    private String country;

    /**
     * Location (general)
     */
    @Column(name = "location", length = 255)
    private String location;

    /**
     * Website URL
     */
    @Column(name = "website", length = 255)
    private String website;

    /**
     * GitHub profile URL
     */
    @Column(name = "github_url", length = 255)
    private String githubUrl;

    /**
     * LinkedIn profile URL
     */
    @Column(name = "linkedin_url", length = 255)
    private String linkedinUrl;

    /**
     * Twitter profile URL
     */
    @Column(name = "twitter_url", length = 255)
    private String twitterUrl;

    /**
     * Facebook profile URL
     */
    @Column(name = "facebook_url", length = 255)
    private String facebookUrl;

    /**
     * Instagram profile URL
     */
    @Column(name = "instagram_url", length = 255)
    private String instagramUrl;

    /**
     * YouTube channel URL
     */
    @Column(name = "youtube_url", length = 255)
    private String youtubeUrl;

    /**
     * Job title
     */
    @Column(name = "job_title", length = 100)
    private String jobTitle;

    /**
     * Company
     */
    @Column(name = "company", length = 100)
    private String company;

    /**
     * School/University
     */
    @Column(name = "school", length = 100)
    private String school;

    /**
     * Skills (comma-separated)
     */
    @Column(name = "skills", length = 1000)
    private String skills;

    /**
     * Interests (comma-separated)
     */
    @Column(name = "interests", length = 1000)
    private String interests;

    /**
     * Languages (comma-separated)
     */
    @Column(name = "languages", length = 255)
    private String languages;

    /**
     * Timezone
     */
    @Column(name = "timezone", length = 50)
    @lombok.Builder.Default
    private String timezone = "Asia/Ho_Chi_Minh";

    /**
     * Locale/Language preference
     */
    @Column(name = "locale", length = 10)
    @lombok.Builder.Default
    private String locale = "vi";

    /**
     * Theme preference
     */
    @Column(name = "theme", length = 20)
    @lombok.Builder.Default
    private String theme = "light";

    /**
     * Privacy level
     */
    @Column(name = "privacy_level", length = 20)
    @lombok.Builder.Default
    private String privacyLevel = "PUBLIC";

    /**
     * Whether profile is public
     */
    @Column(name = "is_public", nullable = false)
    @lombok.Builder.Default
    private Boolean isPublic = true;

    /**
     * Whether to show email publicly
     */
    @Column(name = "show_email", nullable = false)
    @lombok.Builder.Default
    private Boolean showEmail = false;

    /**
     * Whether to show phone publicly
     */
    @Column(name = "show_phone", nullable = false)
    @lombok.Builder.Default
    private Boolean showPhone = false;

    /**
     * Whether to receive email notifications
     */
    @Column(name = "email_notifications", nullable = false)
    @lombok.Builder.Default
    private Boolean emailNotifications = true;

    /**
     * Whether to receive push notifications
     */
    @Column(name = "push_notifications", nullable = false)
    @lombok.Builder.Default
    private Boolean pushNotifications = true;

    /**
     * Get full name
     */
    @Transient
    public String getFullName() {
        if (firstName != null && lastName != null) {
            return firstName + " " + lastName;
        }
        if (firstName != null) {
            return firstName;
        }
        if (lastName != null) {
            return lastName;
        }
        return displayName;
    }

    /**
     * Get initials
     */
    @Transient
    public String getInitials() {
        StringBuilder initials = new StringBuilder();
        if (firstName != null && !firstName.isEmpty()) {
            initials.append(firstName.charAt(0));
        }
        if (lastName != null && !lastName.isEmpty()) {
            initials.append(lastName.charAt(0));
        }
        if (initials.length() == 0 && displayName != null && !displayName.isEmpty()) {
            initials.append(displayName.charAt(0));
        }
        return initials.toString().toUpperCase();
    }

    /**
     * Check if profile is complete
     */
    @Transient
    public boolean isComplete() {
        return firstName != null && lastName != null && 
               bio != null && avatarUrl != null;
    }

    /**
     * Get completion percentage
     */
    @Transient
    public int getCompletionPercentage() {
        int total = 10; // Total fields to check
        int completed = 0;
        
        if (firstName != null && !firstName.isEmpty()) completed++;
        if (lastName != null && !lastName.isEmpty()) completed++;
        if (displayName != null && !displayName.isEmpty()) completed++;
        if (bio != null && !bio.isEmpty()) completed++;
        if (avatarUrl != null && !avatarUrl.isEmpty()) completed++;
        if (phone != null && !phone.isEmpty()) completed++;
        if (location != null && !location.isEmpty()) completed++;
        if (website != null && !website.isEmpty()) completed++;
        if (jobTitle != null && !jobTitle.isEmpty()) completed++;
        if (company != null && !company.isEmpty()) completed++;
        
        return (completed * 100) / total;
    }
}
