package mariana.entity;

import mariana.util.Auth;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDate;


/**
 * Created by mariana on 30.05.2016.
 */
@MappedSuperclass
public abstract class AbstractAuditable {

    @Column(name = "CREATED_BY", nullable = false)
    private String createdBy;

    @Column(name = "CREATED", nullable = false)
    private LocalDate createdDate;

    @Column(name = "LAST_UPDATE_BY", nullable = false)
    private String lastUpdateBy;

    @Column(name = "LAST_UPDATE", nullable = false)
    private LocalDate lastUpdateDate;


    @PrePersist
    public void addCreatedBy() {
        this.createdBy = Auth.userLoggedIn();
        this.createdDate = LocalDate.now();
        this.lastUpdateBy = Auth.userLoggedIn();
        this.lastUpdateDate = LocalDate.now();
    }

    @PreUpdate
    public void addLastUpdateBy() {
        if (Auth.userLoggedIn() != null) {
            this.lastUpdateBy = Auth.userLoggedIn();
            this.lastUpdateDate = LocalDate.now();
        }
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public LocalDate getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDate lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}