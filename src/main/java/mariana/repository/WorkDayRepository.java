package mariana.repository;

import mariana.entity.WorkDay;
import org.joda.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by mariana on 04.06.2016.
 */
public interface WorkDayRepository extends JpaRepository<WorkDay, Long> {
    List<WorkDay> findByUserUsernameAndDayBetween(String username, LocalDate start, LocalDate end);
}
