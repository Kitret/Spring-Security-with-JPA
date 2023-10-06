package com.kitret.springsecurityjpa.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
public class SecurityConfigurer {

    @Autowired
    private UserDetailsService userDetails;

    @Bean
    @Deprecated
    protected PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    // @Bean
    // protected PasswordEncoder passwordEncoder(){
    //     return new BCryptPasswordEncoder();
    // }

    @Bean
    protected DaoAuthenticationProvider dAuthenticationProvider(){
        DaoAuthenticationProvider dAuthenticationProvider = new DaoAuthenticationProvider(passwordEncoder());
        dAuthenticationProvider.setUserDetailsService(userDetails);
        dAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return dAuthenticationProvider;
    }
    
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests((authorizeHttpRequest) -> {
            authorizeHttpRequest.requestMatchers("/admin").hasRole("ADMIN")
            .requestMatchers("/user").hasAnyRole("ADMIN","USER")
            .requestMatchers("/").permitAll();
        })
        .authenticationProvider(dAuthenticationProvider())
        .formLogin(withDefaults());

        return http.build();
    }


}
