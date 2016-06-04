package mariana.service;

import mariana.entity.Project;
import mariana.entity.UserProject;
import mariana.mapper.ProjectMapper;
import mariana.model.ProjectIdNameModel;
import mariana.model.ProjectModel;
import mariana.repository.ProjectRepository;
import mariana.repository.UserProjectRepository;
import mariana.util.Auth;
import mariana.util.ProjectStatus;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mariana on 01.06.2016.
 */
@Service
@Transactional
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserProjectRepository userProjectRepository;

    public void saveProject(ProjectModel projectModel){

        Project project;
        if(projectModel.getId() == null) {
            project = ProjectMapper.toProject(projectModel);
        }
        else {
            project = projectRepository.findOne(projectModel.getId());
            project.setName(projectModel.getName());
            project.setClient(projectModel.getClient());
            project.setDescription(projectModel.getDescription());
            project.setStartDate(LocalDate.parse(projectModel.getStartDate(), DateTimeFormat.forPattern("dd/MM/yy")));
            project.setStartDate(LocalDate.parse(projectModel.getEndDate(), DateTimeFormat.forPattern("dd/MM/yy")));
            project.setHours(projectModel.getHours());
            project.setColor(projectModel.getColor());
            project.setStatus(projectModel.getProjectStatus());
        }

        projectRepository.save(project);
    }

    public ProjectModel getProjectModel(Long id){
        Project project = projectRepository.findOne(id);

        return ProjectMapper.toProjectModel(project);
    }

    public ProjectModel findProject(Long id){
        return ProjectMapper.toProjectModel(projectRepository.findOne(id));
    }

    public List<ProjectModel> getProjectModelList(){
        List<ProjectModel> projectModelList = new ArrayList<>();
        List<Project> projectList = projectRepository.findAll();

        for(Project project : projectList) {
            projectModelList.add(ProjectMapper.toProjectModel(project));
        }

        return projectModelList;
    }

    public List<ProjectIdNameModel> getNotEndedProjectIdNameList(){
        List<Project> projectList = projectRepository.findByStatusNot(ProjectStatus.ENDED);
        List<ProjectIdNameModel> projectModelList = new ArrayList<>();

        for(Project project : projectList){
            ProjectIdNameModel model = new ProjectIdNameModel(project.getId(), project.getName());
            projectModelList.add(model);
        }

        return projectModelList;
    }

    public List<ProjectIdNameModel> findUserProjects(){
        List<UserProject> userProjectList = userProjectRepository.findByUserUsernameAndStatusTrue(Auth.userLoggedIn());
        List<ProjectIdNameModel> projectModelList = new ArrayList<>();

        for(UserProject userProject : userProjectList){
            ProjectIdNameModel model = new ProjectIdNameModel(userProject.getProject().getId(),
                    userProject.getProject().getName());
            projectModelList.add(model);
        }

        return projectModelList;
    }
}
