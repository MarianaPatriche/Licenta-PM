package mariana.service;

import mariana.entity.Employee;
import mariana.entity.User;
import mariana.entity.UserRole;
import mariana.mapper.EmployeeMapper;
import mariana.model.EmployeeModel;
import mariana.repository.EmployeeRepository;
import mariana.repository.UserRepository;
import mariana.repository.UserRoleRepository;
import mariana.util.MailSenderService;
import mariana.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

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

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MailSenderService mailSenderService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public void saveEmployee(EmployeeModel employeeModel){
		Employee employee = EmployeeMapper.toEmployee(employeeModel);
		employee.setSickDays(SICK_DAYS);

		User user = new User();
		String password = UUID.randomUUID().toString().substring(0, 5);
		user.setPassword(passwordEncoder.encode(password));
		user.setEnabled(Boolean.TRUE);
		user.setUsername(getGeneratedUsername(employee));
		userRepository.save(user);

		employee.setUser(user);
		employeeRepository.save(employee);
		mailSenderService.sendNewAccountEmail(employee, password);
	}

	public Page<Employee> getEmployeeList(Pageable pageable){
		return employeeRepository.findAll(pageable);
	}

	private String getGeneratedUsername(Employee employee){
		String username = employee.getFirstName() + "." + employee.getLastName();
		if(userRepository.findByUsername(username) != null){
			username = username + employee.getBirthday().getDayOfMonth();
		}
		return username;
	}
}
