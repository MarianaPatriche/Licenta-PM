package mariana.controller;

import mariana.model.AllocationModel;
import mariana.model.UserIdUsernameModel;
import mariana.service.AllocationService;
import mariana.service.ProjectService;
import mariana.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by mariana on 02.06.2016.
 */
@Controller
@RequestMapping("/allocate")
public class AllocationController extends BaseController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private AllocationService allocationService;

    @RequestMapping("/form")
    public String allocationForm(Model model){
        model.addAttribute("allocation", new AllocationModel());
        model.addAttribute("projectList", projectService.getNotEndedProjectIdNameList());

        return "allocation/form";
    }

    @RequestMapping("/getEmployees")
    @ResponseBody
    public List<UserIdUsernameModel> getEmployees(@RequestParam String search){
        return userService.getEmployees(search);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String allocationSave(@Valid @ModelAttribute("allocation") AllocationModel allocationModel, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("allocation", allocationModel);
            return "allocation/form";
        }
        allocationService.save(allocationModel);
        return "redirect:/";
    }
}
