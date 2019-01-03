package ro.rosmof.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(
        basePackages = {"ro.rosmof.*"},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "ro.rosmof.controller.*")})
public class RootConfig {

}
