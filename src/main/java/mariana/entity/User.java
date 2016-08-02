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

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled")
    private Boolean enabled;

    @OneToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public User(String password, String username, Boolean enabled) {
        this.password = password;
        this.username = username;
        this.enabled = enabled;
    }

    public User() {
    }
}
