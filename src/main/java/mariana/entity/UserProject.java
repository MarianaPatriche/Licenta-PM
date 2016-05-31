package mariana.entity;

import javax.persistence.*;

/**
 * Created by mariana on 31.05.2016.
 */
@Entity
@Table(name = "USER_PROJECT")
public class UserProject {

    @Id
    @GeneratedValue(generator = "USER_PROJECT_SEQ_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "USER_PROJECT_SEQ_GEN", sequenceName = "USER_PROJECT_SEQ")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
