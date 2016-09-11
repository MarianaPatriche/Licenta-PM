package mariana.repository;

import mariana.entity.UserAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mariana on 11.09.2016.
 */
public interface UserAdminRepository extends JpaRepository<UserAdmin, Long> {
    UserAdmin findByUsername(String username);
}
