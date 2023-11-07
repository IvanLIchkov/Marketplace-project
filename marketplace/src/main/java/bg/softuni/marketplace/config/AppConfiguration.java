package bg.softuni.marketplace.config;

import bg.softuni.marketplace.web.IpBlacklistInterceptor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfiguration implements WebMvcConfigurer {

    private final IpBlacklistInterceptor ipBlacklistInterceptor;


    public AppConfiguration(IpBlacklistInterceptor ipBlacklistInterceptor) {
        this.ipBlacklistInterceptor = ipBlacklistInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(ipBlacklistInterceptor);
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }
}
