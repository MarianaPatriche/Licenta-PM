package mariana.entity;

import javax.persistence.*;

/**
 * Created by mariana on 12.09.2016.
 */
@Entity
@Table(name = "ADMIN_ROLE")
public class AdminRole {

    @Id
    @GeneratedValue(generator = "ADMIN_ROLE_SEQ_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ADMIN_ROLE_SEQ_GEN", sequenceName = "ADMIN_ROLE_SEQ")
    private Long id;

    @Column(name = "ROLE", nullable = false)
    private String role;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserAdmin user;

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

    public UserAdmin getUser() {
        return user;
    }

    public void setUser(UserAdmin user) {
        this.user = user;
    }

    public AdminRole(String role, UserAdmin user) {
        this.role = role;
        this.user = user;
    }
}
