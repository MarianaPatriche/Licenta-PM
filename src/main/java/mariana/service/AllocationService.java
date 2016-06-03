package mariana.service;

import mariana.entity.UserProject;
import mariana.model.AllocationModel;
import mariana.repository.ProjectRepository;
import mariana.repository.UserProjectRepository;
import mariana.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by mariana on 03.06.2016.
 */
@Service
@Transactional
public class AllocationService {

    @Autowired
    private UserProjectRepository userProjectRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    public void save(AllocationModel allocationModel){
        UserProject userProject = new UserProject();
        userProject.setProject(projectRepository.findOne(allocationModel.getProjectId()));
        userProject.setUser(userRepository.findOne(allocationModel.getUserId()));
        userProject.setStatus(Boolean.TRUE);

        userProjectRepository.save(userProject);
    }
}
