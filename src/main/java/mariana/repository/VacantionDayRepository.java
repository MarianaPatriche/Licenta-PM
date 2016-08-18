package mariana.repository;

import mariana.entity.VacantionDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by mariana on 04.06.2016.
 */
public interface VacantionDayRepository extends JpaRepository<VacantionDay, Long> {
    List<VacantionDay> findByEmployeeUserUsernameAndDayBetween(String username, LocalDate start, LocalDate end);
}
