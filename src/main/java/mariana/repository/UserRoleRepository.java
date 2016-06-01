package mariana.repository;

import mariana.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mariana on 01.06.2016.
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Long>{
}
