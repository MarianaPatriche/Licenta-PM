package mariana.repository;

import mariana.entity.EmployeeProject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by mariana on 03.06.2016.
 */
public interface EmployeeProjectRepository extends JpaRepository<EmployeeProject, Long> {
    List<EmployeeProject> findByProjectIdAndStatus(Long projectId, Boolean status);

    List<EmployeeProject> findByEmployeeUserUsernameAndStatusTrue(String username);
}
