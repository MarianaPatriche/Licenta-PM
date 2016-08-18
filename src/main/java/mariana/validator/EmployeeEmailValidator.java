package mariana.validator;

import mariana.model.EmployeeModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by mariana on 15.08.2016.
 */
@Component
public class EmployeeEmailValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return EmployeeModel.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        String email = ((EmployeeModel) o).getEmail();
        if(!email.contains("@") && !email.contains(".")){
            errors.rejectValue("email", "email.invalid");
        }
    }
}
