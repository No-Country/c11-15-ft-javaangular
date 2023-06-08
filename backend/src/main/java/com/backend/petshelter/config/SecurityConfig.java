package com.backend.petshelter.config;

import com.backend.petshelter.security.AccountSecurityDetailsService;
import com.backend.petshelter.security.jwt.JwtAuthorizationFilter;
import com.backend.petshelter.util.enums.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Autowired
    private AccountSecurityDetailsService accountSecurityDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        AuthenticationManagerBuilder auth = http.getSharedObject(
                AuthenticationManagerBuilder.class
        );
        auth.userDetailsService(accountSecurityDetailsService).passwordEncoder(passwordEncoder);

        AuthenticationManager authenticationManager = auth.build();

        http.cors();
        http.csrf().disable();
        http.authenticationManager(authenticationManager);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeHttpRequests()
                .requestMatchers(HttpMethod.PUT,"/api/account/change/**","api/account/findallcustomerlist").hasRole(Role.ADMIN.name())
                .requestMatchers(HttpMethod.PUT, "/api/account/updateAccount/**").hasRole(Role.USER.name())
                .requestMatchers(HttpMethod.POST,"/api/wishlist/**").hasRole(Role.USER.name())
                .requestMatchers("/api/authentication/sign-in",
                        "/api/authentication/sign-up",
                        "/swagger-ui/**",
                        "/v3/**",
                        "/api/account/updateAccount/**",
                        "/pet/**",
                        "/api/account/verify/**"
                ).permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter(){

        return new JwtAuthorizationFilter();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer(){

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }
}