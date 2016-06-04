package mariana.repository;

import mariana.entity.WorkDay;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mariana on 04.06.2016.
 */
public interface WorkDayRepository extends JpaRepository<WorkDay, Long> {
}
