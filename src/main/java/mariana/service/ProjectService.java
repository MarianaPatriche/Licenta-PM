package mariana.service;

import mariana.entity.Project;
import mariana.entity.EmployeeProject;
import mariana.mapper.ProjectMapper;
import mariana.model.ProjectIdNameModel;
import mariana.model.ProjectModel;
import mariana.repository.ProjectRepository;
import mariana.repository.EmployeeProjectRepository;
import mariana.util.Auth;
import mariana.util.DateUtils;
import mariana.util.ProjectStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    private EmployeeProjectRepository employeeProjectRepository;

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
            project.setStartDate(DateUtils.toLocalDate(projectModel.getStartDate()));
            project.setStartDate(DateUtils.toLocalDate(projectModel.getEndDate()));
            project.setHours(projectModel.getHours());
            project.setColor(projectModel.getColor());
            project.setStatus(projectModel.getProjectStatus());
        }

        projectRepository.save(project);
    }

    public ProjectModel getProjectModel(Long id) throws Exception{
        Project project = projectRepository.findOne(id);

        return ProjectMapper.toProjectModel(project);
    }

    public ProjectModel findProject(Long id) throws Exception{
        return ProjectMapper.toProjectModel(projectRepository.findOne(id));
    }

    public Page<Project> getProjectList(PageRequest pageRequest){
        Page<Project> projectList = projectRepository.findAll(pageRequest);

        return projectList;
    }

    public List<ProjectIdNameModel> getNotEndedProjectIdNameList(){
        List<Project> projectList = projectRepository.findByStatusNot(ProjectStatus.Terminat);
        List<ProjectIdNameModel> projectModelList = new ArrayList<>();

        for(Project project : projectList){
            ProjectIdNameModel model = new ProjectIdNameModel(project.getId(), project.getName());
            projectModelList.add(model);
        }

        return projectModelList;
    }

    public List<ProjectIdNameModel> findUserProjects(){
        List<EmployeeProject> employeeProjectList = employeeProjectRepository.findByEmployeeUserUsernameAndStatusTrue(Auth.userLoggedIn());
        List<ProjectIdNameModel> projectModelList = new ArrayList<>();

        for(EmployeeProject employeeProject : employeeProjectList){
            ProjectIdNameModel model = new ProjectIdNameModel(employeeProject.getProject().getId(),
                    employeeProject.getProject().getName());
            projectModelList.add(model);
        }

        return projectModelList;
    }
}
