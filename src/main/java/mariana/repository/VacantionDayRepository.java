package mariana.repository;

import mariana.entity.VacantionDay;
import org.joda.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by mariana on 04.06.2016.
 */
public interface VacantionDayRepository extends JpaRepository<VacantionDay, Long> {
    List<VacantionDay> findByUserUsernameAndDayBetween(String username, LocalDate start, LocalDate end);
}
