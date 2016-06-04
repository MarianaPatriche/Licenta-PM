package mariana.controller;

import mariana.model.FreeDayModel;
import mariana.service.FreeDayService;
import mariana.util.FreeDayType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

/**
 * Created by mariana on 04.06.2016.
 */
@Controller
@RequestMapping("/freeDay")
public class FreeDayController extends BaseController{

    @Autowired
    public FreeDayService freeDayService;

    @RequestMapping("/form")
    public String form(Model model){
        model.addAttribute("freeDayModel", new FreeDayModel());
        model.addAttribute("dayType", Arrays.asList(FreeDayType.values()));

        return "freeDay/form";
    }

    @RequestMapping("/save")
    public String save(@ModelAttribute("freeDayModel") FreeDayModel freeDayModel){

        freeDayService.save(freeDayModel);
        return "redirect:/freeDay/form";
    }

}
