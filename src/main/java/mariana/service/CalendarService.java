package mariana.service;

import mariana.entity.SickDay;
import mariana.entity.VacantionDay;
import mariana.entity.WorkDay;
import mariana.model.ProjectCalendarModel;
import mariana.repository.SickDayRepository;
import mariana.repository.VacantionDayRepository;
import mariana.repository.WorkDayRepository;
import mariana.util.Auth;
import mariana.util.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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

    public List<ProjectCalendarModel> getProjectCalendarForUser(LocalDate localDate, String employee) throws Exception{
        List<ProjectCalendarModel> finalList = new ArrayList<>();
        finalList.addAll(getWorkDays(localDate, employee));
        finalList.addAll(getSickDays(localDate, employee));
        finalList.addAll(getVacationDays(localDate, employee));

        return finalList;
    }

    private List<ProjectCalendarModel> getWorkDays(LocalDate date, String employee) throws Exception{
        List<WorkDay> workDays = workDayRepository.findByEmployeeUserUsernameAndDayBetween(
               employee, date.minusMonths(1), date.plusMonths(1));


        List<ProjectCalendarModel> finalList = new ArrayList<>();

        for(WorkDay workDay : workDays){
            ProjectCalendarModel model = new ProjectCalendarModel();

            model.setBackgroundColor(workDay.getProject().getColor());
            model.setId(workDay.getProject().getId());
            model.setStart(workDay.getDay().toString());
            model.setTitle(workDay.getProject().getName());
            model.setUrl("/project/detail/" + workDay.getProject().getId());
            model.setBorderColor(workDay.getProject().getColor());

            finalList.add(model);
        }
        return finalList;
    }

    private List<ProjectCalendarModel> getVacationDays(LocalDate date, String employee) throws Exception{
        List<VacantionDay> vacationDays = vacantionDayRepository.findByEmployeeUserUsernameAndDayBetween(
                employee, date.minusMonths(1), date.plusMonths(1));

        List<ProjectCalendarModel> finalList = new ArrayList<>();

        for(VacantionDay vacantionDay : vacationDays){
            ProjectCalendarModel model = new ProjectCalendarModel();

            model.setBackgroundColor("#eeeeee");
            model.setStart(vacantionDay.getDay().toString());
            model.setTitle("Concediu");
            model.setBorderColor("#bbbbbb");

            finalList.add(model);
        }
        return finalList;
    }

    private List<ProjectCalendarModel> getSickDays(LocalDate date, String employee) throws Exception{
        List<SickDay> sickDays = sickDayRepository.findByEmployeeUserUsernameAndDayBetween(
                employee, date.minusMonths(1), date.plusMonths(1));

        List<ProjectCalendarModel> finalList = new ArrayList<>();

        for(SickDay sickDay : sickDays){
            ProjectCalendarModel model = new ProjectCalendarModel();

            model.setBackgroundColor("#8B0000");
            model.setStart(sickDay.getDay().toString());
            model.setTitle("Sick day");
            model.setBorderColor("#8B0000");

            finalList.add(model);
        }
        return finalList;
    }

    public List<ProjectCalendarModel> getEmployees(LocalDate date, Long projectId){
        List<WorkDay> workDays = workDayRepository.findByProjectIdAndDayBetween(
                projectId, date.minusMonths(1), date.plusMonths(1));

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + workDays.size() + "   " + date);


        List<ProjectCalendarModel> finalList = new ArrayList<>();

        for(WorkDay workDay : workDays){
            ProjectCalendarModel model = new ProjectCalendarModel();

            model.setBackgroundColor(workDay.getProject().getColor());
            model.setId(workDay.getProject().getId());
            model.setStart(workDay.getDay().toString());
            model.setTitle(workDay.getEmployee().getFirstName() + " " + workDay.getEmployee().getLastName());
            model.setUrl("/project/detail/" + workDay.getProject().getId());
            model.setBorderColor(workDay.getProject().getColor());

            finalList.add(model);
        }
        return finalList;
    }
}
