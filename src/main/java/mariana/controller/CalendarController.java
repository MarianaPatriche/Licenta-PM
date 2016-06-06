package mariana.controller;

import mariana.model.ProjectCalendarModel;
import mariana.service.CalendarService;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by mariana on 04.06.2016.
 */
@Controller
@RequestMapping("/calendar")
public class CalendarController extends BaseController{

    @Autowired
    private CalendarService calendarService;

    @RequestMapping("/projects")
    public String getProjectCalendar(){
        return "calendar/userProject";
    }

    @RequestMapping("/projects/data")
    @ResponseBody
    public List<ProjectCalendarModel> getProjectCalendarData(@RequestParam(name = "date", required = false) String date){
        if(date != null) {
            return calendarService.getProjectCalendarForUser(LocalDate.parse(date, DateTimeFormat.forPattern("dd/MM/yy")));
        }
        return calendarService.getProjectCalendarForUser(LocalDate.now());
    }
}
