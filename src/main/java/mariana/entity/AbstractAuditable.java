package mariana.entity;

import mariana.util.Auth;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

/**
 * Created by mariana on 30.05.2016.
 */
@MappedSuperclass
public abstract class AbstractAuditable {

    @Column(name = "CREATED_BY", nullable = false)
    private String createdBy;

    @Column(name = "CREATED", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "LAST_UPDATE_BY", nullable = false)
    private String lastUpdateBy;

    @Column(name = "LAST_UPDATE", nullable = false)
    private LocalDateTime lastUpdateDate;


    @PrePersist
    public void addCreatedBy() {
        this.createdBy = Auth.userLoggedIn();
        this.createdDate = LocalDateTime.now();
        this.lastUpdateBy = Auth.userLoggedIn();
        this.lastUpdateDate = LocalDateTime.now();
    }

    @PreUpdate
    public void addLastUpdateBy() {
        if (Auth.userLoggedIn() != null) {
            this.lastUpdateBy = Auth.userLoggedIn();
            this.lastUpdateDate = LocalDateTime.now();
        }
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}