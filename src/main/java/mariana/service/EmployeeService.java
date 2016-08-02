package mariana.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mariana.entity.Employee;
import mariana.mapper.EmployeeMapper;
import mariana.model.EmployeeModel;
import mariana.repository.EmployeeRepository;

/**
 * Created by mariana on 02.08.2016.
 */
@Service
@Transactional
public class EmployeeService {

	@Value("${sick.days}")
	private static Long SICK_DAYS;

	@Autowired
	private EmployeeRepository employeeRepository;

	public void saveEmployee(EmployeeModel employeeModel){
		Employee employee = EmployeeMapper.toEmployee(employeeModel);
		employee.setSickDays(SICK_DAYS);

		employeeRepository.save(employee);
	}
}
