package mariana.model;

/**
 * Created by mariana on 04.06.2016.
 */
public class WorkDayModel {

    private Long projectId;

    private String day;

    private Integer hours;

    private String details;

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "WorkDayModel{" +
                "projectId=" + projectId +
                ", day='" + day + '\'' +
                ", hours=" + hours +
                ", details='" + details + '\'' +
                '}';
    }
}
