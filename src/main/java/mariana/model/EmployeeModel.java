package mariana.model;

import java.time.LocalDate;

/**
 * Created by mariana on 02.08.2016.
 */
public class EmployeeModel {
	private Long id;
	private String firstName;
	private String lastName;
	private Long workHours;
	private LocalDate dateOfEmployment;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Long getWorkHours() {
		return workHours;
	}

	public void setWorkHours(Long workHours) {
		this.workHours = workHours;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfEmployment() {
		return dateOfEmployment;
	}

	public void setDateOfEmployment(LocalDate dateOfEmployment) {
		this.dateOfEmployment = dateOfEmployment;
	}

	@Override
	public String toString() {
		return "EmployeeModel{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", workHours=" + workHours +
				", dateOfEmployment=" + dateOfEmployment +
				'}';
	}
}
