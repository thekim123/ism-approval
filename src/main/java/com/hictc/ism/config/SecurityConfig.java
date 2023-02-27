package com.hictc.ism.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeRequests(authorize -> authorize
                        .antMatchers("/image/**", "/subscibe/**", "/comment/**", "/api/**").authenticated()
                        .anyRequest().permitAll())
                .formLogin()
                .loginPage("/auth/signin")        //GET
                .loginProcessingUrl("/auth/signin")
                .defaultSuccessUrl("/")
                .and()
                .build();
    }
}
