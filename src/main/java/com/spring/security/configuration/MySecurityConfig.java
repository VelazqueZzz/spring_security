package com.spring.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
public class MySecurityConfig {
   @Bean
    public InMemoryUserDetailsManager userDetailsService(){
       InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
       manager.createUser(User.withDefaultPasswordEncoder().username("ivan").password("ivan").roles("EMPLOYEE").build());
       manager.createUser(User.withDefaultPasswordEncoder().username("ivan1").password("ivan1").roles("HR").build());
       manager.createUser(User.withDefaultPasswordEncoder().username("ivan2").password("ivan2").roles("MANAGER","HR").build());
       return manager;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
       http.authorizeRequests(authorize->authorize.anyRequest().authenticated()).formLogin(withDefaults()).httpBasic(withDefaults());
       return http.build();
    }
}
