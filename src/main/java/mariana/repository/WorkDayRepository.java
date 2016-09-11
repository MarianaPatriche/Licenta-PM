package mariana.repository;

import mariana.entity.WorkDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by mariana on 04.06.2016.
 */
public interface WorkDayRepository extends JpaRepository<WorkDay, Long> {
    List<WorkDay> findByEmployeeUserUsernameAndDayBetween(String username, LocalDate start, LocalDate end);

    List<WorkDay> findByProjectIdAndDayBetween(Long projectId, LocalDate start, LocalDate end);
}
