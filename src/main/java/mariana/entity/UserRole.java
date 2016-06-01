package mariana.entity;

import javax.persistence.*;

/**
 * Created by mariana on 31.05.2016.
 */
@Entity
@Table(name = "USER_ROLE")
public class UserRole {

    @Id
    @GeneratedValue(generator = "USER_ROLE_SEQ_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "USER_ROLE_SEQ_GEN", sequenceName = "USER_ROLE_SEQ")
    private Long id;

    @Column(name = "ROLE", nullable = false)
    private String role;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserRole(String role, User user) {
        this.role = role;
        this.user = user;
    }
}
