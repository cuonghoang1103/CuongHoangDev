package dev.cuonghoang.entity.user;

import dev.cuonghoang.entity.base.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

/**
 * Role Entity
 * Entity đại diện cho vai trò người dùng
 * 
 * @author CuongHoang
 * @since 1.0.0
 */
@Entity
@Table(name = "roles", indexes = {
    @Index(name = "idx_role_name", columnList = "name", unique = true)
})
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"users"})
@ToString(exclude = {"users"})
public class Role extends AuditableEntity {

    /**
     * Role name (ADMIN, USER, MODERATOR, etc.)
     */
    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    /**
     * Role description
     */
    @Column(name = "description", length = 255)
    private String description;

    /**
     * Role color for UI display
     */
    @Column(name = "color", length = 7)
    @Builder.Default
    private String color = "#6366f1";

    /**
     * Role icon for UI display
     */
    @Column(name = "icon", length = 50)
    @Builder.Default
    private String icon = "fas fa-user";

    /**
     * Role priority (higher number = higher priority)
     */
    @Column(name = "priority")
    @Builder.Default
    private Integer priority = 0;

    /**
     * Whether this role is active
     */
    @Column(name = "active", nullable = false)
    @Builder.Default
    private Boolean active = true;

    /**
     * Users with this role
     */
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @Builder.Default
    private Set<User> users = new HashSet<>();

    /**
     * Add user to this role
     */
    public void addUser(User user) {
        users.add(user);
        user.getRoles().add(this);
    }

    /**
     * Remove user from this role
     */
    public void removeUser(User user) {
        users.remove(user);
        user.getRoles().remove(this);
    }

    /**
     * Get role display name
     */
    @Transient
    public String getDisplayName() {
        if (name == null) return "";
        
        // Convert ROLE_ADMIN to Admin, ROLE_USER to User, etc.
        String displayName = name.replace("ROLE_", "");
        return displayName.substring(0, 1).toUpperCase() + 
               displayName.substring(1).toLowerCase();
    }

    /**
     * Check if this is admin role
     */
    @Transient
    public boolean isAdmin() {
        return "ROLE_ADMIN".equals(name) || "ADMIN".equals(name);
    }

    /**
     * Check if this is user role
     */
    @Transient
    public boolean isUser() {
        return "ROLE_USER".equals(name) || "USER".equals(name);
    }

    /**
     * Check if this is moderator role
     */
    @Transient
    public boolean isModerator() {
        return "ROLE_MODERATOR".equals(name) || "MODERATOR".equals(name);
    }
}
