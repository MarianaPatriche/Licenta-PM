package mariana.mapper;

import mariana.entity.Project;
import mariana.model.ProjectModel;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

/**
 * Created by mariana on 01.06.2016.
 */
public class ProjectMapper {

    public static ProjectModel toProjectModel(Project project){
        ProjectModel projectModel = new ProjectModel();

        projectModel.setId(project.getId());
        projectModel.setName(project.getName());
        projectModel.setClient(project.getClient());
        projectModel.setStartDate(project.getStartDate().toString("dd/MM/yy"));
        projectModel.setEndDate(project.getEndDate().toString("dd/MM/yy"));
        projectModel.setColor(project.getColor());
        projectModel.setHours(project.getHours());
        projectModel.setDescription(project.getDescription());

        return projectModel;
    }

    public static Project toProject(ProjectModel projectModel){
        Project project = new Project();

        project.setName(projectModel.getName());
        project.setClient(projectModel.getClient());
        project.setStartDate(LocalDate.parse(projectModel.getStartDate(), DateTimeFormat.forPattern("dd/MM/yy")));
        project.setEndDate(LocalDate.parse(projectModel.getEndDate(), DateTimeFormat.forPattern("dd/MM/yy")));
        project.setColor(projectModel.getColor());
        project.setHours(projectModel.getHours());
        project.setDescription(projectModel.getDescription());

        return project;
    }
}
