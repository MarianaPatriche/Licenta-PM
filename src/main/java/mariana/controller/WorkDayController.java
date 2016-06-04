package mariana.controller;

import mariana.model.WorkDayModel;
import mariana.service.ProjectService;
import mariana.service.WorkDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mariana on 04.06.2016.
 */
@Controller
@RequestMapping("/workDay")
public class WorkDayController extends BaseController {

    @Autowired
    private WorkDayService workDayService;

    @Autowired
    private ProjectService projectService;

    @RequestMapping("/form")
    public String workDayForm(Model model){
        model.addAttribute("projectList", projectService.findUserProjects());
        model.addAttribute("workDayModel", new WorkDayModel());
        return "workDay/form";
    }

    @RequestMapping("/save")
    public String saveWorkDays(@ModelAttribute("workDayModel") WorkDayModel workDayModel){
        workDayService.save(workDayModel);

        return "workDay/form";
    }
}
