package mariana.repository;

import mariana.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by mariana on 02.08.2016.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByUserUsername(String username);

    @Query(value = "select * from employee where active = true and (first_name like ? or" +
            " last_name like ? )", nativeQuery = true)
    List<Employee> findByFirstNameContainingOrLastNameContainingAllIgnoreCase(String firstName, String lastName);
}
