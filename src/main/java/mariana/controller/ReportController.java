package mariana.controller;

import mariana.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by mariana on 11.09.2016.
 */

@Controller
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping("/")
    public String getMenu(){
        return "reports/home";
    }

    @RequestMapping("/employees")
    public String getReportEmployee(Model model){
        model.addAttribute("employeeList", employeeRepository.findByActiveTrue());
        return "reports/employee";
    }

    @RequestMapping("/generate")
    public String generate(@RequestParam("employeeId") Long employeeId,
                           @RequestParam("startDate") String startDate,
                           @RequestParam("endDate") String endDate){
        System.out.println(">>>>>>>>>>>>>>>>>>>>>" + employeeId + startDate + endDate);
        return "reports/employee";
    }
}
