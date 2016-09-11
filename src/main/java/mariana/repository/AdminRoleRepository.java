package mariana.repository;

import mariana.entity.AdminRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mariana on 12.09.2016.
 */
public interface AdminRoleRepository extends JpaRepository<AdminRole, Long> {
}
