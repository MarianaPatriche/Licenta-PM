package mariana.repository;

import mariana.entity.Specification;
import mariana.util.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by mariana on 11.09.2016.
 */
public interface SpecificationRepository extends JpaRepository<Specification, Long> {
    List<Specification> findByPriorityAndProjectId(Priority priority, Long projectId);
}
