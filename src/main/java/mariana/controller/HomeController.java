package mariana.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mariana on 01.06.2016.
 */

@Controller
public class HomeController extends BaseController{

    @RequestMapping("/")
    public String home(){
        return "home";
    }
}
