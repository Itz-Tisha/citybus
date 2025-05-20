package com.example.citybus.security;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    // User authentication from database
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager udm = new JdbcUserDetailsManager(dataSource);
        
        // Custom SQL queries to fetch users and their roles
        udm.setUsersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?");
        udm.setAuthoritiesByUsernameQuery("SELECT username, role FROM roles WHERE username = ?");
        
        return udm;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // For hashed passwords
    }


    // Security rules for City Bus System
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
           .requestMatchers(HttpMethod.POST, "/citybus/addbus").hasRole("ADMIN")
           .requestMatchers(HttpMethod.DELETE, "/citybus/deleteBus").hasRole("ADMIN")
           .requestMatchers(HttpMethod.DELETE, "/citybus/deleteAllbus").hasRole("ADMIN")
           .requestMatchers(HttpMethod.PUT, "/citybus/updatebusById").hasRole("ADMIN")
           .requestMatchers(HttpMethod.GET, "/citybus/findAlltickets").hasRole("ADMIN")
           .requestMatchers(HttpMethod.GET, "/citybus/findAllpass").hasRole("ADMIN")
//              
                .requestMatchers(HttpMethod.POST, "/citybus/bookTicket").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/citybus/findByPassengername").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/citybus/findByPassId").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/citybus/findticketById").hasRole("USER")
                .requestMatchers(HttpMethod.POST, "/citybus/deleteticket").hasRole("USER")
//               
                .anyRequest().permitAll()
        );

        // Basic authentication
        http.httpBasic(Customizer.withDefaults());

        // Disable CSRF for API calls (optional for REST APIs)
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}
