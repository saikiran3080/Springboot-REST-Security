package com.luv2code.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.net.http.HttpRequest;

@Configuration
public class DemoSecurityConfig {

//    code for defining users
    @Bean
    public InMemoryUserDetailsManager memoryUserDetailsManager(){
        UserDetails Saikiran = User.builder().username("Saikiran")
                .password("{noop}test123").roles("EMPLOYEE").build();
        UserDetails Vikrant = User.builder().username("Vikrant")
                .password("{noop}test123").roles("EMPLOYEE","MANAGER").build();
        UserDetails Sandeep = User.builder().username("Sandeep")
                .password("{noop}test123").roles("EMPLOYEE","MANAGER","ADMIN").build();
        return new InMemoryUserDetailsManager(Saikiran , Vikrant , Sandeep);
    }

//    code for restrictiong users based on roles
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http.authorizeHttpRequests(configurer->
                configurer
                        .requestMatchers(HttpMethod.GET ,"api/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET ,"api/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST ,"api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT ,"api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE ,"api/employees/**").hasRole("ADMIN")


        );

//        using http basic authentication
        http.httpBasic(Customizer.withDefaults());

//        disable cross site request frogery (csfr)
//        in general not required for stateless REST api's that uses GET ,POST ,PUT , DELETE r PATCH
//        csrf used for web application with html form
      http.csrf(csrf->csrf.disable());

      return http.build();
    }

}
