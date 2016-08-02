package mariana.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mariana.entity.Employee;

/**
 * Created by mariana on 02.08.2016.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
