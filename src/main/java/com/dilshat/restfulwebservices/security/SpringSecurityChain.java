package com.dilshat.restfulwebservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityChain {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //All requests should be authenticated
        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated()
        );
        //If a request is not authenticated, a web page is shown
        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
