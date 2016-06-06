package mariana.repository;

import mariana.entity.SickDay;
import org.joda.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by mariana on 04.06.2016.
 */
public interface SickDayRepository extends JpaRepository<SickDay, Long> {
    List<SickDay> findByUserUsernameAndDayBetween(String username, LocalDate start, LocalDate end);
}
