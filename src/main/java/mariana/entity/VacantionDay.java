package mariana.entity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * Created by mariana on 31.05.2016.
 */
@Entity
@Table(name = "VACANTION_DAY")
public class VacantionDay {

    @Id
    @GeneratedValue(generator = "VACANTION_DAY_SEQ_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "VACANTION_DAY_SEQ_GEN", sequenceName = "VACANTION_DAY_SEQ")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @Column(name = "day")
    private LocalDate day;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }
}
