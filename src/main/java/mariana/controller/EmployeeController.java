package mariana.controller;

import mariana.model.EmployeeModel;
import mariana.service.EmployeeService;
import mariana.validator.EmployeeEmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Created by mariana on 02.08.2016.
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController extends BaseController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeEmailValidator employeeEmailValidator;

	@RequestMapping("/form")
	public String form(Model model) {
		model.addAttribute("employeeModel", new EmployeeModel());
		return "employee/form";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String projectInsert(@Valid @ModelAttribute("employeeModel") EmployeeModel employeeModel, BindingResult bindingResult, Model model) {
		employeeEmailValidator.validate(employeeModel, bindingResult);
		if(bindingResult.hasErrors()){
			return "employee/form";
		}
		employeeService.saveEmployee(employeeModel);
		return "redirect:/";
	}

	@RequestMapping(value = "/list")
	public String employeeList(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
							  @RequestParam(value = "size", defaultValue = "2") int size){
		model.addAttribute("page", employeeService.getEmployeeList(new PageRequest(page, size)));
		return "employee/list";
	}
}
