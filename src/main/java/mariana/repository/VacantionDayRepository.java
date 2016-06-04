package mariana.repository;

import mariana.entity.VacantionDay;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mariana on 04.06.2016.
 */
public interface VacantionDayRepository extends JpaRepository<VacantionDay, Long> {
}
