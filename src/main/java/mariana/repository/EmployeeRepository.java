package mariana.repository;

import mariana.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mariana on 02.08.2016.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByUserUsername(String username);
}
