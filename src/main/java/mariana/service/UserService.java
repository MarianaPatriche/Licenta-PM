package mariana.service;

import mariana.entity.User;
import mariana.model.ProfileModel;
import mariana.repository.UserRepository;
import mariana.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by mariana on 03.06.2016.
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ProfileModel getProfileModel(){
        ProfileModel profileModel = new ProfileModel();
        profileModel.setUsername(Auth.userLoggedIn());

        return profileModel;
    }

    public void saveNewPassword(String password){
        User user = userRepository.findByUsername(Auth.userLoggedIn());
        user.setPassword(passwordEncoder.encode(password));

        userRepository.save(user);
    }

    public void savePicturePath(String path){
        User user = userRepository.findByUsername(Auth.userLoggedIn());
        user.setPicture(path);
        userRepository.save(user);
    }
}
