package mariana.util;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by mariana on 30.05.2016.
 */
public class Auth {

    public static String userLoggedIn() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
