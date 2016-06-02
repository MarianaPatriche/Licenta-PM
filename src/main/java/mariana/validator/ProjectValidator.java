package mariana.validator;

import mariana.entity.Project;
import mariana.model.ProjectModel;
import mariana.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * Created by mariana on 01.06.2016.
 */
@Component
public class ProjectValidator implements Validator {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public boolean supports(Class<?> x) {
        return ProjectModel.class.isAssignableFrom(x);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ProjectModel projectModel = (ProjectModel) o;
        List<Project> projectList = projectRepository.findByName(projectModel.getName());

            if (projectList.size() > 1) {
                errors.rejectValue("name", "project.name.already.exist");
            }
    }

}
