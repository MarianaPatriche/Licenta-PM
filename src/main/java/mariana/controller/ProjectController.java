package mariana.controller;

import mariana.entity.Project;
import mariana.entity.Specification;
import mariana.model.ProjectModel;
import mariana.model.SpecificationModel;
import mariana.repository.ProjectRepository;
import mariana.repository.SpecificationRepository;
import mariana.service.AllocationService;
import mariana.service.ProjectService;
import mariana.util.Priority;
import mariana.util.ProjectStatus;
import mariana.util.SpecificationStatus;
import mariana.validator.ProjectValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private AllocationService allocationService;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private SpecificationRepository specificationRepository;

    @RequestMapping(value = "/form/{id}", method = RequestMethod.GET)
    public String projectForm(@PathVariable("id") Long id, Model model) throws Exception {
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
        return "redirect:/project/list";
    }

    @RequestMapping(value = "/list")
    public String projectList(Model model, @RequestParam(value = "page", required = true, defaultValue = "0") int page,
                              @RequestParam(value = "size", required = true, defaultValue = "5") int size){
        model.addAttribute("page", projectService.getProjectList(new PageRequest(page, size)));
        return "project/list";
    }

    @RequestMapping(value = "/detail/{id}")
    public String projectDetail(@PathVariable("id") Long id , Model model) throws  Exception{
        model.addAttribute("project", projectService.getProjectModel(id));
        model.addAttribute("actualTeam", allocationService.getTeamByProject(id, Boolean.TRUE));
        model.addAttribute("oldTeam", allocationService.getTeamByProject(id, Boolean.FALSE));
        model.addAttribute("taskHigh", specificationRepository.findByPriorityAndProjectId(Priority.Ridicata, id));
        model.addAttribute("taskMedium", specificationRepository.findByPriorityAndProjectId(Priority.Medie, id));
        model.addAttribute("taskLow", specificationRepository.findByPriorityAndProjectId(Priority.Scazuta, id));

        return "project/detail";
    }

    @RequestMapping(value = "/specification/{projectId}", method = RequestMethod.GET)
    public String getSpecificationModel(Model model, @PathVariable("projectId") Long projectId){
        SpecificationModel specificationModel = new SpecificationModel();
        specificationModel.setProjectId(projectId);
        model.addAttribute("specification", specificationModel);
        model.addAttribute("priorities", Arrays.asList(Priority.values()));
        return "project/specification";
    }

    @RequestMapping(value = "/specification/save", method = RequestMethod.POST)
    public String saveSpecification(@ModelAttribute("specification") SpecificationModel specificationModel){
        Specification specification = new Specification();
        Project project = projectRepository.findOne(specificationModel.getProjectId());

        specification.setDescription(specificationModel.getDescription());
        specification.setHours(specificationModel.getWorkHours());
        specification.setName(specificationModel.getName());
        specification.setPriority(specificationModel.getPriority());
        specification.setStatus(SpecificationStatus.Nou);
        specification.setProject(project);

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + specification);
        specificationRepository.save(specification);

        return "redirect:/project/list";
    }
}