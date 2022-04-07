package com.example.proiect_endava.config;

import com.example.proiect_endava.dto.CustomUserDetails;
import com.example.proiect_endava.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserServiceImpl userService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        CustomUserDetails stefanUser = userService.loadUserByUsername("stefan@gmail.com");
        CustomUserDetails stefanOwner = userService.loadUserByUsername("marcu@gmail.com");
        auth.inMemoryAuthentication()
                .withUser(stefanUser);
        auth.inMemoryAuthentication().withUser(stefanOwner)
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception  {
        http.authorizeRequests()
                .antMatchers(
                        "/signUp*","/js/*","/css/*", "/images/*",
                        "/resources/*", "/static/*").permitAll()
                .mvcMatchers("/", "/car","/addcar","/submitCar","/editCar",
                        "/addAppointment","/submitAppointment","/deleteCar","/findOneCar",
                        "/car/{id}").hasRole("USER")
                .mvcMatchers("/","/autoService","/addAutoService",
                        "/submitAutoService","editAutoService","/deleteAutoService","/submitService",
                        "/autoService/{id}").hasRole("SERVEROWNER")
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll()
                .and()
                .csrf().disable();
    }

    @Override
    public void configure(WebSecurity web)
    {
        web.ignoring()
                .antMatchers( "/static/*", "/resources/static/*","/resources/*", "/js/*", "/css/*", "/images/*");

    }
}
