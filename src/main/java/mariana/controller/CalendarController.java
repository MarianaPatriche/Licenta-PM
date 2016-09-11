package mariana.controller;

import mariana.model.ProjectCalendarModel;
import mariana.repository.EmployeeRepository;
import mariana.repository.ProjectRepository;
import mariana.service.CalendarService;
import mariana.util.Auth;
import mariana.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by mariana on 04.06.2016.
 */
@Controller
@RequestMapping("/calendar")
public class CalendarController extends BaseController{

    @Autowired
    private CalendarService calendarService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @RequestMapping("/")
    public String getCalendarsMenu(){
        return "calendar/home";
    }

    @RequestMapping("/employees")
    public String getEmployeeCalendar(){
        return "calendar/userProject";
    }

    @RequestMapping("/employees/data")
    @ResponseBody
    public List<ProjectCalendarModel> getProjectCalendarData(@RequestParam(name = "date", required = false) String date,
                                                             @RequestParam(name = "employee", required = false) Long employeeId) throws Exception{
        if(date != null) {
            if(date.length() < 10){
                date = date.substring(0,3) + "0" + date.substring(3, date.length());
            }
        }
        String employee = "";
        if(employeeId != null){
            employee = employeeRepository.findOne(employeeId).getUser().getUsername();
        }
        if(date != null && employeeId != null) {
            return calendarService.getProjectCalendarForUser(DateUtils.toLocalDate(date), employee);
        }
        else
        if(employeeId != null && date == null) {
            return calendarService.getProjectCalendarForUser(LocalDate.now(), employee);
        }
        else if(date != null){
            return calendarService.getProjectCalendarForUser(DateUtils.toLocalDate(date), Auth.userLoggedIn());
        }
        return calendarService.getProjectCalendarForUser(LocalDate.now(), Auth.userLoggedIn());
    }

    @RequestMapping("/projects")
    public String getProjectCalendar(Model model) {
        model.addAttribute("projects", projectRepository.findAll());
        return "calendar/project";
    }

    @RequestMapping("/project/data")
    @ResponseBody
    public List<ProjectCalendarModel> getEmployeeCalendarData(@RequestParam(name = "date", required = false) String date,
                                                             @RequestParam(name = "employee", required = false) Long projectId) throws Exception{
        if(date != null) {
            if(date.length() < 10){
                date = date.substring(0,3) + "0" + date.substring(3, date.length());
            }
        }

        if(date != null && projectId != null) {
            return calendarService.getEmployees(DateUtils.toLocalDate(date), projectId);
        }
        else
        if(projectId != null && date == null) {
            return calendarService.getEmployees(LocalDate.now(), projectId);
        }
        else if(date != null){
            return calendarService.getEmployees(DateUtils.toLocalDate(date), 0L);
        }
        return calendarService.getEmployees(LocalDate.now(), 0L);
    }

}
