package mariana.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

import mariana.model.EmployeeModel;

/**
 * Created by mariana on 02.08.2016.
 */
@Controller
@RequestMapping("/employee")
public class EmployeeControler extends BaseController {

	@RequestMapping("/form")
	public String form(Model model) {
		model.addAttribute("employeeModel", new EmployeeModel());

		return "employee/form";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String projectInsert(@Valid @ModelAttribute("employeeModel") EmployeeModel employeeModel, BindingResult bindingResult, Model model) {


		return "redirect:/";
	}

}
