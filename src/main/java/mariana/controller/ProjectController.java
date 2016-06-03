package mariana.controller;

import mariana.model.ProjectModel;
import mariana.repository.ProjectRepository;
import mariana.service.ProjectService;
import mariana.util.ProjectStatus;
import mariana.validator.ProjectValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Arrays;

/**
 * Created by mariana on 01.06.2016.
 */

@Controller
@RequestMapping("/project")
public class ProjectController extends BaseController{

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectValidator projectValidator;

    @RequestMapping(value = "/form/{id}", method = RequestMethod.GET)
    public String projectForm(@PathVariable("id") Long id, Model model) {
        if(id != 0) {
            model.addAttribute("project", projectService.findProject(id));
        }
        else {
            model.addAttribute("project", new ProjectModel());
        }
        model.addAttribute("statuses",  Arrays.asList(ProjectStatus.values()));

        return "project/form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String projectInsert(@Valid @ModelAttribute("project") ProjectModel projectModel, BindingResult bindingResult, Model model) {
        projectValidator.validate(projectModel, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("project", projectModel);
            return "project/form";
        }
        projectService.saveProject(projectModel);
        return "redirect:/";
    }

    @RequestMapping(value = "/list")
    public String projectList(Model model){
        model.addAttribute("projectList", projectService.getProjectModelList());
        return "project/list";
    }

    @RequestMapping(value = "/detail/{id}")
    public String projectDetail(@PathVariable("id") Long id , Model model){
        model.addAttribute("project", projectService.getProjectModel(id));

        return "project/detail";
    }
}