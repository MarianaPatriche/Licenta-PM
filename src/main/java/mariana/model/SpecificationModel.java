package mariana.model;

import mariana.util.Priority;

/**
 * Created by mariana on 11.09.2016.
 */
public class SpecificationModel {
    Long projectId;
    String name;
    String description;
    Long workHours;
    Priority priority;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getWorkHours() {
        return workHours;
    }

    public void setWorkHours(Long workHours) {
        this.workHours = workHours;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
