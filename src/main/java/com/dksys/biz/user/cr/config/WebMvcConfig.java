package com.dksys.biz.user.cr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import com.dksys.biz.user.cr.cr03.util.MessageUtils;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Bean
    public MappingJackson2JsonView jsonView() {
        return new MappingJackson2JsonView();
    }
    
    @Bean
    public MessageUtils messageUtils() {
        return new MessageUtils();
    }
    
//    @Bean
//    public ViewResolver jspViewResolver() {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix("/html/");
//        viewResolver.setSuffix(".html");
//        return viewResolver;
//    }
    
//    @Bean
//    public WebClientUtil webClientUtil() {
//        return new WebClientUtil();
//    }
    
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedMethods("GET", "POST", "PUT", "DELETE")
//                .maxAge(3000);
//    }
}
