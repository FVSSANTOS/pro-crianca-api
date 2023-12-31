package com.procrianca.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.procrianca.demo.domain.security.jwt.JwtAuthFilter;
import com.procrianca.demo.domain.security.jwt.JwtService;
import com.procrianca.demo.service.impl.UserServiceImpl;



@Configuration
public class SecurityConfig {

    @Autowired
    private UserServiceImpl usuarioService;
    @Autowired
    private JwtService jwtService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth ->
                auth
                    .requestMatchers(HttpMethod.GET,"/api/v1/users/**")
                        .permitAll()
                    .requestMatchers(HttpMethod.POST,"/api/v1/users/**")
                        .hasAnyRole("ADMIN","USER")
                    .requestMatchers(HttpMethod.GET,"/api/v1/beneficiaries/**")
                        .hasAnyRole("ADMIN","USER")
                    .requestMatchers(HttpMethod.POST,"/api/v1/beneficiaries/**")
                        .hasAnyRole("ADMIN","USER")
                    .requestMatchers(HttpMethod.POST,"/api/v1/images/**")
                        .hasAnyRole("ADMIN","USER")
                    .requestMatchers(HttpMethod.PUT,"/api/v1/beneficiaries/**")
                        .hasAnyRole("ADMIN","USER")
                    .requestMatchers(HttpMethod.DELETE,"/api/v1/beneficiaries/**")
                        .hasAnyRole("ADMIN","USER")
                    .requestMatchers(HttpMethod.GET,"/api/v1/collaborators/**")
                        .hasAnyRole("ADMIN","USER")
                    .requestMatchers(HttpMethod.POST,"/api/v1/collaborators/**")
                        .hasAnyRole("ADMIN","USER")
                    .requestMatchers(HttpMethod.PUT,"/api/v1/collaborators/**")
                        .hasAnyRole("ADMIN","USER")
                    .requestMatchers(HttpMethod.DELETE,"/api/v1/collaborators/**")
                        .hasAnyRole("ADMIN","USER")
                    .requestMatchers(HttpMethod.GET,"/api/v1/units/**")
                        .hasAnyRole("ADMIN","USER")
                    .requestMatchers(HttpMethod.POST,"/api/v1/units/**")
                        .hasAnyRole("ADMIN","USER")
                    .requestMatchers(HttpMethod.PUT,"/api/v1/units/**")
                        .hasAnyRole("ADMIN","USER")
                    .requestMatchers(HttpMethod.DELETE,"/api/v1/units/**")
                        .hasAnyRole("ADMIN","USER")
                    .requestMatchers(HttpMethod.POST, "/api/v1/auth")
                        .permitAll()
                    .requestMatchers(HttpMethod.GET, "/v3/api-docs/**")
                        .permitAll()
                    .requestMatchers(HttpMethod.GET, "/swagger-ui/**")
                        .permitAll()
                    .anyRequest()
                        .authenticated()
            )
            .sessionManagement(session -> session
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public OncePerRequestFilter jwtFilter(){
        return new JwtAuthFilter(jwtService, usuarioService);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public FilterRegistrationBean<CustomCorsFilter> corsFilter() {
        FilterRegistrationBean<CustomCorsFilter> registrationBean =
                new FilterRegistrationBean<>(new CustomCorsFilter());
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registrationBean;
    }
}
