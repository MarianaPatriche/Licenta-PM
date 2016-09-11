package mariana.validator;


import mariana.model.ProfileModel;
import mariana.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by mariana on 21.08.2016.
 */
@Component
public class PasswordValidator implements Validator {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean supports(Class<?> aClass) {
      return ProfileModel.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ProfileModel profileModel = (ProfileModel) o;
        if((profileModel.getNewPassword() != null || profileModel.getRepeatPassword() != null) &&
                (!profileModel.getNewPassword().equals("") || !profileModel.getRepeatPassword().equals("")) ) {
            if(profileModel.getNewPassword().length() < 4){
                errors.rejectValue("newPassword", "password.length");
            }

            if (!profileModel.getNewPassword().equals(profileModel.getRepeatPassword())) {
                errors.rejectValue("repeatPassword", "password.repeat");
            }

           /* User user = userRepository.findByUsername(Auth.userLoggedIn());
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + user.getPassword());
            if (!(user.getPassword()).equals(passwordEncoder.encode(profileModel.getOldPassword()))) {
                System.out.println(">>>>>>>> " + profileModel.getOldPassword() + "  " + passwordEncoder.encode(profileModel.getOldPassword()));
                errors.rejectValue("oldPassword", "password.incorect");
            }*/
        }
    }
}
