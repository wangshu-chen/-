package com.bishe.teamproject.cors;

import com.bishe.teamproject.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CrosConfig implements WebMvcConfigurer {

    @Bean
    public WebMvcConfigurer corsConfigs() {
        return new WebMvcConfigurer() {
            public void addCorsMappings (CorsRegistry registry){
                registry.addMapping("/**")
//                    .allowCredentials(true)
                       .allowedOrigins("*")
                       .allowedMethods("*")
                       .allowedHeaders("*")
                        .maxAge(3600L)
            ;
          }
        };
    }

    private CorsConfiguration corsConfig(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setMaxAge(3600L);
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }

    /**
     * 注册拦截器
     */

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(new LoginInterceptor());
        //添加要拦截内容
        interceptorRegistration.addPathPatterns("/**");
        //添加不拦截的内容
        interceptorRegistration.excludePathPatterns("/users/loginIn","/users/queryuserInfo","/users/loginout","team/queryTeamAll","activity/findByTeamId");

    }
}
