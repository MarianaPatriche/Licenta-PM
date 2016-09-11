package mariana.controller;

import mariana.model.ProfileModel;
import mariana.service.UserService;
import mariana.validator.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by mariana on 20.08.2016.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    public static final String ROOT = "src/main/resources/static/img/upload-dir";

    private final ResourceLoader resourceLoader;

    @Autowired
    public UserController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordValidator passwordValidator;

    @RequestMapping("/profile")
    public String getProfile(Model model){
        model.addAttribute("profile", userService.getProfileModel());
        return  "profile/form";
    }

    @RequestMapping("/profile/save")
    public String saveProfile(Model model, @Valid @ModelAttribute("profile") ProfileModel profile, BindingResult bindingResult,
                              @RequestParam("picture") MultipartFile file,
                              RedirectAttributes redirectAttributes) throws IOException{

        passwordValidator.validate(profile, bindingResult);
        if(bindingResult.hasErrors()){
            return "profile/form";
        }

        if(profile.getNewPassword() != null && !profile.getNewPassword().equals("")){
            userService.saveNewPassword(profile.getNewPassword());
        }

        if(!Files.exists(Paths.get(ROOT))) {
            Files.createDirectory(Paths.get(ROOT));
        }
        if (!file.isEmpty()) {
            try {
                Files.copy(file.getInputStream(), Paths.get(ROOT, file.getOriginalFilename()));
                redirectAttributes.addFlashAttribute("message",
                        "You successfully uploaded " + file.getOriginalFilename() + "!");
            } catch (IOException|RuntimeException e) {
                redirectAttributes.addFlashAttribute("message", "Failued to upload " + file.getOriginalFilename() + " => " + e.getMessage());
            }
            userService.savePicturePath(Paths.get("/img/upload-dir", file.getOriginalFilename()).toString());
        } else {
            redirectAttributes.addFlashAttribute("message", "Failed to upload " + file.getOriginalFilename() + " because it was empty");
        }

        return "redirect:/logout";
    }
}
