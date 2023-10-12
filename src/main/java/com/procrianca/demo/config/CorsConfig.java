package com.procrianca.demo.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // Defina as origens permitidas
                .allowedMethods("GET", "POST", "OPTIONS") // Defina os métodos permitidos
                .allowCredentials(false); // Define se você permite credenciais (por padrão, é falso)
    }
}
