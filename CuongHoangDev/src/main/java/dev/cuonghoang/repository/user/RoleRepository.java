package dev.cuonghoang.repository.user;

import dev.cuonghoang.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Role Data Access Repository
 * Repository xử lý truy cập dữ liệu vai trò
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Tìm role theo tên
     */
    Optional<Role> findByName(String name);

    /**
     * Kiểm tra role đã tồn tại
     */
    boolean existsByName(String name);
}
