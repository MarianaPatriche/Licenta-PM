package mariana.service;

import mariana.entity.SickDay;
import mariana.entity.VacantionDay;
import mariana.entity.WorkDay;
import mariana.model.ProjectCalendarModel;
import mariana.repository.SickDayRepository;
import mariana.repository.VacantionDayRepository;
import mariana.repository.WorkDayRepository;
import mariana.util.Auth;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mariana on 05.06.2016.
 */
@Transactional
@Service
public class CalendarService {

    @Autowired
    private WorkDayRepository workDayRepository;

    @Autowired
    private VacantionDayRepository vacantionDayRepository;

    @Autowired
    private SickDayRepository sickDayRepository;

    public List<ProjectCalendarModel> getProjectCalendarForUser(LocalDate localDate){
        List<ProjectCalendarModel> finalList = new ArrayList<>();
        finalList.addAll(getWorkDays(localDate));
        finalList.addAll(getSickDays(localDate));
        finalList.addAll(getVacationDays(localDate));

        return finalList;
    }

    private List<ProjectCalendarModel> getWorkDays(LocalDate date){
        List<WorkDay> workDays = workDayRepository.findByUserUsernameAndDayBetween(
                Auth.userLoggedIn(), date.minusMonths(1), date.plusMonths(1));

        List<ProjectCalendarModel> finalList = new ArrayList<>();

        for(WorkDay workDay : workDays){
            ProjectCalendarModel model = new ProjectCalendarModel();

            model.setBackgroundColor(workDay.getProject().getColor());
            model.setId(workDay.getProject().getId());
            model.setStart(workDay.getDay().toString("yyyy-MM-dd"));
            model.setTitle(workDay.getProject().getName());
            model.setUrl("/project/detail/" + workDay.getProject().getId());
            model.setBorderColor(workDay.getProject().getColor());

            finalList.add(model);
        }
        return finalList;
    }

    private List<ProjectCalendarModel> getVacationDays(LocalDate date){
        List<VacantionDay> vacationDays = vacantionDayRepository.findByUserUsernameAndDayBetween(
                Auth.userLoggedIn(), date.minusMonths(1), date.plusMonths(1));

        List<ProjectCalendarModel> finalList = new ArrayList<>();

        for(VacantionDay vacantionDay : vacationDays){
            ProjectCalendarModel model = new ProjectCalendarModel();

            model.setBackgroundColor("#eeeeee");
            model.setStart(vacantionDay.getDay().toString("yyyy-MM-dd"));
            model.setTitle("Concediu");
            model.setBorderColor("#bbbbbb");

            finalList.add(model);
        }
        return finalList;
    }

    private List<ProjectCalendarModel> getSickDays(LocalDate date){
        List<SickDay> sickDays = sickDayRepository.findByUserUsernameAndDayBetween(
                Auth.userLoggedIn(), date.minusMonths(1), date.plusMonths(1));

        List<ProjectCalendarModel> finalList = new ArrayList<>();

        for(SickDay sickDay : sickDays){
            ProjectCalendarModel model = new ProjectCalendarModel();

            model.setBackgroundColor("#8B0000");
            model.setStart(sickDay.getDay().toString("yyyy-MM-dd"));
            model.setTitle("Sick day");
            model.setBorderColor("#8B0000");

            finalList.add(model);
        }
        return finalList;
    }
}
