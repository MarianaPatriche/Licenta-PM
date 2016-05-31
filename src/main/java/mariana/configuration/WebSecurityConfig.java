package mariana.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.util.ReflectionUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Created by mariana on 30.05.2016.
 */
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private DataSource dataSource;

    private static FormLoginConfigurer<HttpSecurity> customFailureHandler(
            FormLoginConfigurer<HttpSecurity> loginPage) {
        loginPage.failureHandler(new SimpleUrlAuthenticationFailureHandler("/login?error") {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                                AuthenticationException exception) throws IOException, ServletException {
                request.getSession().setAttribute("loginFailedUsername", request.getParameter("username"));
                super.onAuthenticationFailure(request, response, exception);
            }
        });
        Field field =
                ReflectionUtils.findField(AbstractAuthenticationFilterConfigurer.class, "failureUrl");
        field.setAccessible(true);
        ReflectionUtils.setField(field, loginPage, "/login?error");
        return loginPage;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder();
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled "
                        + "from users where username = ? and TRUE")
                .authoritiesByUsernameQuery(
                        "select u.username, ur.role from users u, user_role ur  "
                                + "where  ur.user_id = u.id and u.username = ?")
                .passwordEncoder(passwordEncoder());
    }


    @Configuration
    @EnableGlobalMethodSecurity(securedEnabled = true)
    @Order(2)
    public static class BaseAuthConfiguration extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            customFailureHandler(
                    http.csrf().disable().exceptionHandling().and().formLogin().loginPage("/login"))
                    .permitAll().defaultSuccessUrl("/").and().logout().logoutSuccessUrl("/login")
                    .and().authorizeRequests().antMatchers("/css/**", "/js/**", "/register").permitAll().anyRequest()
                    .authenticated();
        }

    }
}