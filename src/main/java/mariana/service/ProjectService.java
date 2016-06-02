package mariana.service;

import mariana.entity.Project;
import mariana.mapper.ProjectMapper;
import mariana.model.ProjectModel;
import mariana.repository.ProjectRepository;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by mariana on 01.06.2016.
 */
@Service
@Transactional
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

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
}
