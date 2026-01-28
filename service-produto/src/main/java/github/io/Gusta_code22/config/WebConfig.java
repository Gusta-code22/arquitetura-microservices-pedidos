package github.io.Gusta_code22.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:3000", "http://my-front.com") Para futuro Front
                .allowedOriginPatterns("*")
                .allowedMethods("PUT", "POST", "GET", "PATCH", "DELETE", "OPTIONS")
                .maxAge(3600)
                .allowCredentials(true);
    }
}
