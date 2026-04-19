package dev.cuonghoang.entity.base;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Base Entity Class
 * Lớp entity cơ sở cho tất cả các entity khác
 * 
 * @author CuongHoang
 * @since 1.0.0
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Primary Key - Auto Increment Long
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    /**
     * Version for optimistic locking
     */
    @Version
    @Column(name = "version")
    private Long version;

    /**
     * Check if entity is new (not persisted yet)
     */
    @Transient
    public boolean isNew() {
        return this.id == null;
    }

    /**
     * Override equals method
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        BaseEntity that = (BaseEntity) obj;
        return id != null && id.equals(that.id);
    }

    /**
     * Override hashCode method
     */
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
