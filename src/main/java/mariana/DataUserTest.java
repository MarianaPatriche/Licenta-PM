package mariana;

import mariana.entity.User;
import mariana.entity.UserRole;
import mariana.repository.UserRepository;
import mariana.repository.UserRoleRepository;
import mariana.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * Created by mariana on 01.06.2016.
 */
@Service
@Transactional
public class DataUserTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initData() {

        /**
         * Check for empty database.
         */

        if (userRepository.count() > 0) {
            return;
        }

        /**
         * ADMIN USER.
         */
		User admin = new User("admin", passwordEncoder.encode("admin"), true);
        admin.setFirstName("admin");
        admin.setLastName("admin");
		admin = userRepository.save(admin);
		UserRole userRoleAdmin = new UserRole(Role.ROLE_ADMIN.name(), admin);
		userRoleRepository.save(userRoleAdmin);

        User user = new User("user", passwordEncoder.encode("user"), true);
        user.setFirstName("user");
        user.setLastName("user");
        user = userRepository.save(user);
        UserRole userRole = new UserRole(Role.ROLE_USER.name(), user);
        userRoleRepository.save(userRole);
    }
}
