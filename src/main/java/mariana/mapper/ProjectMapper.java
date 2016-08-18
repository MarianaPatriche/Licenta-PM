package mariana.mapper;

import mariana.entity.Project;
import mariana.model.ProjectModel;
import mariana.util.DateUtils;

/**
 * Created by mariana on 01.06.2016.
 */
public class ProjectMapper {

    public static ProjectModel toProjectModel(Project project) throws Exception{
        ProjectModel projectModel = new ProjectModel();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>. " + project);
        projectModel.setId(project.getId());
        projectModel.setName(project.getName());
        projectModel.setClient(project.getClient());
        projectModel.setStartDate(DateUtils.dateToString(project.getStartDate()));
        projectModel.setEndDate(DateUtils.dateToString(project.getEndDate()));
        projectModel.setColor(project.getColor());
        projectModel.setHours(project.getHours());
        projectModel.setDescription(project.getDescription());
        projectModel.setProjectStatus(project.getStatus());

        return projectModel;
    }

    public static Project toProject(ProjectModel projectModel){
        Project project = new Project();

        project.setName(projectModel.getName());
        project.setClient(projectModel.getClient());
        System.out.println(">>>>>>>>>>>>>>>>>>>>>> " + projectModel.getStartDate());
        project.setStartDate(DateUtils.toLocalDate(projectModel.getStartDate()));
        System.out.println(">>>>>>>>>>>>>" + project.getStartDate());
        project.setEndDate(DateUtils.toLocalDate(projectModel.getEndDate()));
        project.setColor(projectModel.getColor());
        project.setHours(projectModel.getHours());
        project.setDescription(projectModel.getDescription());
        project.setStatus(projectModel.getProjectStatus());

        return project;
    }
}
