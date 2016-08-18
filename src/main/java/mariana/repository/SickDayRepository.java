package mariana.repository;

import mariana.entity.SickDay;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by mariana on 04.06.2016.
 */
public interface SickDayRepository extends JpaRepository<SickDay, Long> {
    List<SickDay> findByEmployeeUserUsernameAndDayBetween(String username, LocalDate start, LocalDate end);
}
