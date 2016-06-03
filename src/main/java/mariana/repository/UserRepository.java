package mariana.repository;

import mariana.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by mariana on 01.06.2016.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByEnabledTrueAndUsernameContaining(String a);
}
