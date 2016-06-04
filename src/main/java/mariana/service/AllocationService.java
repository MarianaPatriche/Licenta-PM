package mariana.service;

import mariana.entity.UserProject;
import mariana.model.AllocationModel;
import mariana.model.AllocationUserModel;
import mariana.repository.ProjectRepository;
import mariana.repository.UserProjectRepository;
import mariana.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    public List<AllocationUserModel> getTeamByProject(Long projectId, Boolean status){
            return getTeamModelList(userProjectRepository.findByProjectIdAndStatus(projectId, status));
    }

    private List<AllocationUserModel> getTeamModelList(List<UserProject> userProjectList){
        List<AllocationUserModel> modelList = new ArrayList<>();
        for(UserProject userProject : userProjectList){
            AllocationUserModel model = new AllocationUserModel();
            model.setName(userProject.getUser().getFirstName() + " " + userProject.getUser().getLastName());
            model.setStartDate(userProject.getCreatedDate().toString("dd/mm/yyyy"));
            model.setEndDate(userProject.getLastUpdateDate().toString("dd/mm/yyyy"));
            model.setAllocationId(userProject.getId());

            modelList.add(model);
        }

        return modelList;
    }

    public Long changeAllocationStatut(Long allocationId){
        UserProject userProject = userProjectRepository.findOne(allocationId);
        userProject.setStatus(!userProject.getStatus());

        return userProject.getProject().getId();
    }
}
