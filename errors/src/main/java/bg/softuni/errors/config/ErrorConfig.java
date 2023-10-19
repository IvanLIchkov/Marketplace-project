package bg.softuni.errors.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.Properties;

@Configuration
public class ErrorConfig {

    @Bean
    public HandlerExceptionResolver simpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver smer = new SimpleMappingExceptionResolver();

        Properties properties = new Properties();
        properties.setProperty(NullPointerException.class.getSimpleName(), "npe");

        smer.setExceptionMappings(properties);

        return smer;
    }
}

//    @Bean
//    public HandlerExceptionResolver customHandlerExceptionResolver() { TODO custom handler resolver
//        return (request, response, handler, ex) -> {
//
//            if (ex instanceof NullPointerException) {
//                ModelAndView modelAndView = new ModelAndView("npe");
//                return modelAndView;
//            }
//            return null;
//        };
//    }
//}
