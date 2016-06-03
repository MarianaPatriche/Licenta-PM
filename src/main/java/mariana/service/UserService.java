package mariana.service;

import mariana.entity.User;
import mariana.model.UserIdUsernameModel;
import mariana.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mariana on 03.06.2016.
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserIdUsernameModel> getEmployees(String search){
        List<User> userList = userRepository.findByEnabledTrueAndUsernameContaining(search);
        List<UserIdUsernameModel>  modelList = new ArrayList<>();

        for(User user : userList){
            UserIdUsernameModel model = new UserIdUsernameModel(user.getId(), user.getUsername());
            modelList.add(model);
        }

        return modelList;
    }
}
