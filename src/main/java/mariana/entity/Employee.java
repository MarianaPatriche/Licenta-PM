package mariana.entity;


import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by mariana on 02.08.2016.
 */

@Entity
@Table(name = "EMPLOYEE")
public class Employee{
	@Id
	@GeneratedValue(generator = "EMPLOYEE_SEQ_GEN", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "EMPLOYEE_SEQ_GEN", sequenceName = "EMPLOYEE_SEQ")
	private Long id;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "date_of_employment")
	private LocalDate dateOfEmployment;

	@Column(name = "work_hours")
	private Long workHours;

	@Column(name = "vacantion_days")
	private Long vacantionDays;

	@Column(name = "sick_days")
	private Long sickDays;

	@Column(name = "email")
	private String email;

	@Column(name = "birthday")
	private LocalDate birthday;

	@OneToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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

	public Long getWorkHours() {
		return workHours;
	}

	public void setWorkHours(Long workHours) {
		this.workHours = workHours;
	}

	public Long getVacantionDays() {
		return vacantionDays;
	}

	public void setVacantionDays(Long vacantionDays) {
		this.vacantionDays = vacantionDays;
	}

	public Long getSickDays() {
		return sickDays;
	}

	public void setSickDays(Long sickDays) {
		this.sickDays = sickDays;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
