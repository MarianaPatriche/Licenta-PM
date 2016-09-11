package mariana.entity;

import mariana.util.Priority;
import mariana.util.ProjectStatus;
import mariana.util.SpecificationStatus;

import javax.persistence.*;

/**
 * Created by mariana on 11.09.2016.
 */

@Entity
@Table(name = "Specification")
public class Specification {

    @Id
    @GeneratedValue(generator = "SPECIFICATION_SEQ_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SPECIFICATION_SEQ_GEN", sequenceName = "SPECIFICATION_SEQ")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "hours")
    private Long hours;

    @Column(name = "description")
    private String description;

    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private SpecificationStatus status;

    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    private Project project;

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

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }


    public SpecificationStatus getStatus() {
        return status;
    }

    public void setStatus(SpecificationStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Specification{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hours=" + hours +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", status=" + status +
                ", project=" + project +
                '}';
    }
}
