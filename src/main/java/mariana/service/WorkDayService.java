package mariana.service;

import mariana.entity.WorkDay;
import mariana.model.WorkDayModel;
import mariana.repository.EmployeeRepository;
import mariana.repository.ProjectRepository;
import mariana.repository.WorkDayRepository;
import mariana.util.Auth;
import mariana.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by mariana on 04.06.2016.
 */

@Service
@Transactional
public class WorkDayService {

    @Autowired
    private WorkDayRepository workDayRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public void save(WorkDayModel workDayModel){
        WorkDay workDay = new WorkDay();

        workDay.setEmployee(employeeRepository.findByUserUsername(Auth.userLoggedIn()));
        workDay.setProject(projectRepository.findOne(workDayModel.getProjectId()));
        workDay.setDay(DateUtils.toLocalDate(workDayModel.getDay()));
        workDay.setDetails(workDayModel.getDetails());
        workDay.setHours(workDayModel.getHours());

        workDayRepository.save(workDay);
    }

}
