package mariana.repository;

import mariana.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mariana on 01.06.2016.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
