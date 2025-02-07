package com.luv2code.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

// add support for jdbc no more hardcoded users
    @Bean
//    inject datasource autoconfigured by springboot
    public UserDetailsManager userDetailsManager(DataSource dataSource)
    {
//        tell spring security to use JDBC authentication with our datasource
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        define a query to retrieve a user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_id , pw , active from members where user_id=?"
        );
//        define a query to retrieve an authority/role by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_id ,role from roles where user_id=?"
        );

        return jdbcUserDetailsManager;
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

    /*
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
    */

}
