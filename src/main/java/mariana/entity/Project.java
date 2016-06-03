package mariana.entity;

import mariana.util.ProjectStatus;
import org.joda.time.LocalDate;

import javax.persistence.*;


/**
 * Created by mariana on 31.05.2016.
 */

@Entity
@Table(name = "PROJECT")
public class Project extends AbstractAuditable{

    @Id
    @GeneratedValue(generator = "PROJECT_SEQ_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "PROJECT_SEQ_GEN", sequenceName = "PROJECT_SEQ")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "client")
    private String client;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "color")
    private String color;

    @Column(name = "hours")
    private Long hours;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getHours() {
        return hours;
    }

    public void setHours(Long hours) {
        this.hours = hours;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", client='" + client + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", color='" + color + '\'' +
                ", hours=" + hours +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
