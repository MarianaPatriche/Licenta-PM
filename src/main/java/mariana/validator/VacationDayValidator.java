package mariana.validator;

import mariana.entity.Employee;
import mariana.entity.User;
import mariana.model.FreeDayModel;
import mariana.repository.EmployeeRepository;
import mariana.repository.UserRepository;
import mariana.util.Auth;
import mariana.util.FreeDayType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by mariana on 06.06.2016.
 */

@Component
public class VacationDayValidator implements Validator{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public boolean supports(Class<?> x) {
        return FreeDayModel.class.isAssignableFrom(x);
    }

    @Override
    public void validate(Object o, Errors errors) {
       Employee employee = employeeRepository.findByUserUsername(Auth.userLoggedIn());
        FreeDayModel freeDayModel = (FreeDayModel) o;
        if(freeDayModel.getType().equals(FreeDayType.VACATION_DAY)){
            if (employee.getVacantionDays() < 1) {
                errors.rejectValue("date", "vacationDays.zero");
            }
        }
        else{
            if (employee.getSickDays() < 1) {
                errors.rejectValue("date", "sickDays.zero");
            }
        }
    }
}
