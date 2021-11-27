package com.example.myhospital.config;

import com.example.myhospital.service.AdminService;
import com.example.myhospital.service.DoctorService;
import com.example.myhospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthSuccessHandler successHandler;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @Bean
    public AuthenticationSuccessHandler myAuthSuccessHandler(){
        return new CustomAuthSuccessHandler();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/home", "/js/**", "/css/**","/patient/create","/","/favicon.ico").permitAll()
                .antMatchers("/staff","/home/admin").hasAuthority("ADMIN")
                .antMatchers("/home/receptionist").hasAuthority("RECEPTIONIST")
                .antMatchers("/home/nurse").hasAuthority("NURSE")
                .antMatchers("/home/patient").hasAuthority("PATIENT")
                .antMatchers("/home/doctor").hasAuthority("DOCTOR")
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .successHandler(myAuthSuccessHandler())
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService); //set the custom user details service
        auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
        return auth;
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(doctorService)
//                .passwordEncoder(passwordEncoder());
//        auth.userDetailsService(receptionistService)
//                .passwordEncoder(passwordEncoder());
//        auth.userDetailsService(adminService)
//                .passwordEncoder(passwordEncoder());
//        auth.userDetailsService(nurseService)
//                .passwordEncoder(passwordEncoder());
//        auth.userDetailsService(inPatientService)
//                .passwordEncoder(passwordEncoder());
//        auth.userDetailsService(outPatientService)
//                .passwordEncoder(passwordEncoder());

//    }
}
