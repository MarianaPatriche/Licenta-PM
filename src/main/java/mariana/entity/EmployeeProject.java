package mariana.entity;

import javax.persistence.*;

/**
 * Created by mariana on 31.05.2016.
 */
@Entity
@Table(name = "EMPLOYEE_PROJECT")
public class EmployeeProject extends AbstractAuditable{

    @Id
    @GeneratedValue(generator = "EMPLOYEE_PROJECT_SEQ_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "EMPLOYEE_PROJECT_SEQ_GEN", sequenceName = "EMPLOYEE_PROJECT_SEQ")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @Column(name = "STATUS")
    private Boolean status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
