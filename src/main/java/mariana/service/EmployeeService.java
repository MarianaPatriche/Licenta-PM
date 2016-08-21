package mariana.service;

import mariana.entity.Employee;
import mariana.entity.User;
import mariana.entity.UserRole;
import mariana.mapper.EmployeeMapper;
import mariana.model.EmployeeIdNameModel;
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

import java.util.ArrayList;
import java.util.List;
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
		employee.setActive(Boolean.TRUE);

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

	public List<EmployeeIdNameModel> getSearchEmployeeList(String search){
		List<Employee> employeeList = employeeRepository
				.findByFirstNameContainingOrLastNameContainingAllIgnoreCase("%" + search.toLowerCase() + "%", "%" + search.toLowerCase() + "%");
		List<EmployeeIdNameModel> employeeIdNameModelList = new ArrayList<>();
		for (Employee employee : employeeList){
			EmployeeIdNameModel model = new EmployeeIdNameModel(employee.getId(), employee.getLastName() + " " + employee.getFirstName());
			employeeIdNameModelList.add(model);
		}
		return employeeIdNameModelList;
	}
}
