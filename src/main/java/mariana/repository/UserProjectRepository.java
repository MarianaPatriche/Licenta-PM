package mariana.repository;

import mariana.entity.UserProject;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mariana on 03.06.2016.
 */
public interface UserProjectRepository extends JpaRepository<UserProject, Long> {
}
