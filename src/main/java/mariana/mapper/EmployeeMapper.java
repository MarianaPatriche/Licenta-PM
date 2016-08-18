package mariana.mapper;

import mariana.entity.Employee;
import mariana.model.EmployeeModel;
import mariana.util.DateUtils;

/**
 * Created by mariana on 02.08.2016.
 */
public class EmployeeMapper {

	public static Employee toEmployee(EmployeeModel employeeModel){
		Employee employee = new Employee();
		employee.setDateOfEmployment(DateUtils.toLocalDate(employeeModel.getDateOfEmployment()));
		employee.setFirstName(employeeModel.getFirstName());
		employee.setLastName(employeeModel.getLastName());
		employee.setWorkHours(employeeModel.getWorkHours());
		employee.setEmail(employeeModel.getEmail());
		employee.setVacantionDays(employeeModel.getVacantionDays());
		employee.setBirthday(DateUtils.toLocalDate(employeeModel.getBirthday()));

		return employee;
	}
}
