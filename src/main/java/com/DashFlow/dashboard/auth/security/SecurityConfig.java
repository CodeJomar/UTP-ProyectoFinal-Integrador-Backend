package com.DashFlow.dashboard.auth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    
    // Encripta la contraseña
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    // Retorna datos del usuario (ya registrado)
    @Bean
    public DaoAuthenticationProvider authenticationProvider(PasswordEncoder encoder, UserDetailsService userDetailsService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService);
        auth.setPasswordEncoder(encoder);
        return auth;
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
               .requestMatchers("/", "/registro", "/css/**", "/js/**", "/images/**").permitAll()
               .requestMatchers("/dashboard", "/cuenta", "/productos", "/ingresos", "/pedidos", "/tiers", "/reportes").hasRole("ADMIN")
               .requestMatchers("/dashboard", "/cuenta", "/productos", "/pedidos", "/reportes").hasRole("GERENTE")
               .requestMatchers("/dashboard", "/cuenta", "/pedidos").hasRole("EMPLEADO")
               .anyRequest().authenticated()
            )
            .formLogin(form -> form
               .loginPage("/")
               .loginProcessingUrl("/login")
               .defaultSuccessUrl("/dashboard", true)
               .failureUrl("/dashboard")
               .permitAll()
            )
            .logout(logout -> logout
               .logoutSuccessUrl("/")
               .permitAll()
            );
        return http.build();
    }
}
