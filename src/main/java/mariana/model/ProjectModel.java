package mariana.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Created by mariana on 01.06.2016.
 */
public class ProjectModel {
    private Long id;

    @NotEmpty(message = "Campul trebuie completat!")
    private String name;

    @NotEmpty(message = "Campul trebuie completat!")
    private String client;

    @NotNull(message = "Campul trebuie completat!")
    private String startDate;

    @NotNull(message = "Campul trebuie completat!")
    private String endDate;

    @NotEmpty(message = "Campul trebuie completat!")
    private String color;

    @Min(value = 1, message = "Campul trebuie completat!")
    private Long hours;

    private String description;

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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
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

    @Override
    public String toString() {
        return "ProjectModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", client='" + client + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", color='" + color + '\'' +
                ", hours=" + hours +
                ", description='" + description + '\'' +
                '}';
    }
}
