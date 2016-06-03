package mariana.model;

/**
 * Created by mariana on 02.06.2016.
 */
public class AllocationModel {

    private Long projectId;

    private Long userId;

    private Boolean status;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AllocationModel{" +
                "projectId=" + projectId +
                ", userId=" + userId +
                ", status=" + status +
                '}';
    }
}
