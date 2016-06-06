package mariana.validator;

import mariana.entity.Project;
import mariana.entity.User;
import mariana.model.FreeDayModel;
import mariana.model.ProjectModel;
import mariana.repository.ProjectRepository;
import mariana.repository.UserRepository;
import mariana.util.Auth;
import mariana.util.FreeDayType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * Created by mariana on 06.06.2016.
 */

@Component
public class VacationDayValidator implements Validator{
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean supports(Class<?> x) {
        return FreeDayModel.class.isAssignableFrom(x);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = userRepository.findByUsername(Auth.userLoggedIn());
        FreeDayModel freeDayModel = (FreeDayModel) o;
        if(freeDayModel.getType().equals(FreeDayType.VACATION_DAY)){
            if (user.getVacantionDays() < 1) {
                errors.rejectValue("date", "vacationDays.zero");
            }
        }
        else{
            if (user.getSickDays() < 1) {
                errors.rejectValue("date", "sickDays.zero");
            }
        }
    }
}
