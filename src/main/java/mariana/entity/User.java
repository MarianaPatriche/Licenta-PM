package mariana.entity;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by mariana on 31.05.2016.
 */

@Entity
@Table(name = "USERS")
public class User{

    @Id
    @GeneratedValue(generator = "USER_SEQ_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "USER_SEQ_GEN", sequenceName = "USER_SEQ")
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "date_of_employment")
    private LocalDate dateOfEmployment;

    @Column(name = "work_hours")
    private Long workHours;

    @Column(name = "vacantion_days")
    private Long vacantionDays;

    @Column(name = "sick_days")
    private Long sickDays;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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

    public User(String username, String password, Boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }
}
