package mariana.service;

import mariana.entity.EmployeeProject;
import mariana.model.AllocationModel;
import mariana.model.AllocationUserModel;
import mariana.repository.EmployeeRepository;
import mariana.repository.ProjectRepository;
import mariana.repository.EmployeeProjectRepository;
import mariana.util.DateUtils;
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
    private EmployeeProjectRepository employeeProjectRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public void save(AllocationModel allocationModel){
        EmployeeProject employeeProject = new EmployeeProject();
        employeeProject.setProject(projectRepository.findOne(allocationModel.getProjectId()));
        employeeProject.setEmployee(employeeRepository.findOne(allocationModel.getEmployeeId()));
        employeeProject.setStatus(Boolean.TRUE);

        employeeProjectRepository.save(employeeProject);
    }

    public List<AllocationUserModel> getTeamByProject(Long projectId, Boolean status) throws Exception{
            return getTeamModelList(employeeProjectRepository.findByProjectIdAndStatus(projectId, status));
    }

    private List<AllocationUserModel> getTeamModelList(List<EmployeeProject> employeeProjectList) throws Exception{
        List<AllocationUserModel> modelList = new ArrayList<>();
        for(EmployeeProject employeeProject : employeeProjectList){
            AllocationUserModel model = new AllocationUserModel();
            model.setName(employeeProject.getEmployee().getFirstName() + " " + employeeProject.getEmployee().getLastName());
            model.setStartDate(DateUtils.dateToString(employeeProject.getCreatedDate()));
            model.setEndDate(DateUtils.dateToString(employeeProject.getLastUpdateDate()));
            model.setAllocationId(employeeProject.getId());

            modelList.add(model);
        }

        return modelList;
    }

    public Long changeAllocationStatut(Long allocationId){
        EmployeeProject employeeProject = employeeProjectRepository.findOne(allocationId);
        employeeProject.setStatus(!employeeProject.getStatus());

        return employeeProject.getProject().getId();
    }
}
