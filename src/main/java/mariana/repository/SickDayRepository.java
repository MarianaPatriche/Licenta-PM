package mariana.repository;

import mariana.entity.SickDay;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mariana on 04.06.2016.
 */
public interface SickDayRepository extends JpaRepository<SickDay, Long> {
}
