package mariana.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by mariana on 02.08.2016.
 */
public class EmployeeModel {
	private Long id;

	@NotNull
	@Size(min = 2)
	private String firstName;

	@NotNull
	@Size(min = 2)
	private String lastName;

	@NotNull
	@Min(4)
	@Max(8)
	private Long workHours;

	@NotNull
	private String dateOfEmployment;

	@NotNull
	@Size(min = 2)
	private String email;

	@NotNull
	private Long vacantionDays;

	@NotNull
	private String birthday;

	@NotNull
	private String job;

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

	public String getDateOfEmployment() {
		return dateOfEmployment;
	}

	public void setDateOfEmployment(String dateOfEmployment) {
		this.dateOfEmployment = dateOfEmployment;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getVacantionDays() {
		return vacantionDays;
	}

	public void setVacantionDays(Long vacantionDays) {
		this.vacantionDays = vacantionDays;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
}
