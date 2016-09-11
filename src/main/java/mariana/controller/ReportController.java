package mariana.controller;

import mariana.repository.EmployeeRepository;
import mariana.service.ReportService;
import mariana.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by mariana on 11.09.2016.
 */

@Controller
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ReportService reportService;

    @RequestMapping("/")
    public String getMenu(){
        return "reports/home";
    }

    @RequestMapping("/employees")
    public String getReportEmployee(Model model){
        model.addAttribute("employeeList", employeeRepository.findByActiveTrue());
        return "reports/employee";
    }

    @RequestMapping("/generate/employees")
    public String generate(@RequestParam("employeeId") Long employeeId,
                           @RequestParam("startDate") String startDate,
                           @RequestParam("endDate") String endDate,
                           HttpServletRequest request, HttpServletResponse response) throws Exception{
        System.out.println(">>>>>>>>>>>>>>>>>>>>>" + employeeId + startDate + endDate);

        String path = reportService.generateAndSaveReportForBaseRequest(employeeRepository.findOne(employeeId).getUser().getUsername(),
                DateUtils.toLocalDate(startDate), DateUtils.toLocalDate(endDate));
        reportService.downloadReport(path, request, response);
        return "reports/employee";
    }
}
