package com.example.learn2025.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())   // 停用 CSRF
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/api/v1/member/register").permitAll() //不需登入即可通行
                .requestMatchers("/h2-console/**").permitAll()// 不需登入即可通行
                .anyRequest().authenticated()
            )
            .headers(headers -> headers.frameOptions().disable()); // 允許 H2 控制台
        
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}