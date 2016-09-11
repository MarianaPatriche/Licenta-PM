package mariana.controller;

import mariana.entity.UserAdmin;
import mariana.repository.UserAdminRepository;
import mariana.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by mariana on 01.06.2016.
 */
public class BaseController {

    @Autowired
    private UserAdminRepository userAdminRepository;

    @ModelAttribute("userLoggedIn")
    public String getUserLoggedIn(){
        return Auth.userLoggedIn();
    }

}
