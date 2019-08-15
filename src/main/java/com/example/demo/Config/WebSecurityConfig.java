package com.example.demo.Config;

import com.example.demo.Service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/store")
                .hasAnyRole("ADMIN", "USER")
                .antMatchers("/user/*")
                .hasRole("USER")
                .antMatchers("/admin/*")
                .hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .successHandler((req, res, auth) -> {
                    res.sendRedirect("/login");
                })
                .failureHandler((req, res, exp) -> {
                    String error = "";
                    if (exp.getClass().isAssignableFrom(BadCredentialsException.class)) {
                        error = "Invalid email or password.";
                    } else {
                        error = "Unknown error - " + exp.getMessage();
                    }
                    req.getSession().setAttribute("error", error);
                    res.sendRedirect("/login");
                })
                .and()
                .logout()
                .logoutUrl("/signout")
                .logoutSuccessHandler((req, res, auth) -> {
                    req.getSession().setAttribute("error", "You are successfully sign out");
                    res.sendRedirect("/login");
                })
                .and()
                .csrf().disable();
    }
}
