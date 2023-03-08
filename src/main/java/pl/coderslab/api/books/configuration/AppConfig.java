package pl.coderslab.api.books.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("pl.coderslab.api.books")
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {

}
