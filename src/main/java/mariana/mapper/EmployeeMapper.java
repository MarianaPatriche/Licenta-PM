package mariana.mapper;

import mariana.entity.Employee;
import mariana.model.EmployeeModel;

/**
 * Created by mariana on 02.08.2016.
 */
public class EmployeeMapper {

	public static Employee toEmployee(EmployeeModel employeeModel){
		Employee employee = new Employee();
		employee.setDateOfEmployment(employeeModel.getDateOfEmployment());
		employee.setFirstName(employeeModel.getFirstName());
		employee.setLastName(employeeModel.getLastName());
		employee.setWorkHours(employeeModel.getWorkHours());

		return employee;
	}
}
