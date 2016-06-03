package mariana.repository;

import mariana.entity.Project;
import mariana.util.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by mariana on 01.06.2016.
 */
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByName(String name);

    List<Project> findByStatusNot(ProjectStatus status);
}