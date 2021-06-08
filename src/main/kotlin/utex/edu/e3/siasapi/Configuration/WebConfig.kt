package utex.edu.e3.siasapi.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class WebConfig : WebMvcConfigurer{

    override fun addCorsMappings(registry:CorsRegistry){
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:8080")
            .allowedMethods("GET","POST","PUT","DELETE")
    }
}
