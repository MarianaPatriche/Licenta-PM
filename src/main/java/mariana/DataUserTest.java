package mariana;

import mariana.entity.*;
import mariana.repository.*;
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
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserAdminRepository userAdminRepository;

    @Autowired
    private AdminRoleRepository adminRoleRepository;

    @PostConstruct
    public void initData() {

        /**
         * Check for empty database.
         */

      /*  if (userRepository.count() > 0 && u) {
            return;
        }
*/
        /**
         * ADMIN USER.
         */
      /*  Employee adminEmployee = new Employee();
        adminEmployee.setFirstName("Admin");
        adminEmployee.setLastName("admin");

		UserAdmin admin = new UserAdmin("admin", passwordEncoder.encode("admin"), true);
		admin = userAdminRepository.save(admin);
        AdminRole userRoleAdmin = new AdminRole(Role.ROLE_ADMIN.name(), admin);
		adminRoleRepository.save(userRoleAdmin);*/
/*
        Employee userEmployee = new Employee();
        userEmployee.setFirstName("USER");
        userEmployee.setLastName("user");

        User user = new User("user", passwordEncoder.encode("user"), true);
        user = userRepository.save(user);
        userEmployee.setUser(user);
        userEmployee = employeeRepository.save(userEmployee);
        UserRole userRole = new UserRole(Role.ROLE_USER.name(), user);
        userRoleRepository.save(userRole);*/
    }
}
