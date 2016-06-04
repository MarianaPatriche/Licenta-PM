package mariana.service;

import mariana.entity.User;
import mariana.entity.WorkDay;
import mariana.model.WorkDayModel;
import mariana.repository.ProjectRepository;
import mariana.repository.UserRepository;
import mariana.repository.WorkDayRepository;
import mariana.util.Auth;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
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
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public void save(WorkDayModel workDayModel){
        WorkDay workDay = new WorkDay();

        workDay.setUser(userRepository.findByUsername(Auth.userLoggedIn()));
        workDay.setProject(projectRepository.findOne(workDayModel.getProjectId()));
        workDay.setDay(LocalDate.parse(workDayModel.getDay(), DateTimeFormat.forPattern("dd/MM/yy")));
        workDay.setDetails(workDayModel.getDetails());
        workDay.setHours(workDayModel.getHours());

        workDayRepository.save(workDay);
    }

}
