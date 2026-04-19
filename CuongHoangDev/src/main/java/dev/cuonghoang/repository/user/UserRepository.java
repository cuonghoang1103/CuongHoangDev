package dev.cuonghoang.repository.user;

import dev.cuonghoang.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * User Data Access Repository
 * Repository xử lý truy cập dữ liệu người dùng
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Tìm người dùng theo email
     */
    Optional<User> findByEmail(String email);

    /**
     * Tìm người dùng theo username
     */
    Optional<User> findByUsername(String username);

    /**
     * Kiểm tra email đã tồn tại
     */
    boolean existsByEmail(String email);

    /**
     * Kiểm tra username đã tồn tại
     */
    boolean existsByUsername(String username);

    /**
     * Tìm người dùng theo email hoặc username
     */
    @Query("SELECT u FROM User u WHERE u.email = :emailOrUsername OR u.username = :emailOrUsername")
    Optional<User> findByEmailOrUsername(@Param("emailOrUsername") String emailOrUsername);

    /**
     * Tìm người dùng đang hoạt động
     */
    @Query("SELECT u FROM User u WHERE u.enabled = true")
    java.util.List<User> findActiveUsers();

    /**
     * Tìm người dùng theo role
     */
    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.name = :roleName")
    java.util.List<User> findByRoleName(@Param("roleName") String roleName);
}
