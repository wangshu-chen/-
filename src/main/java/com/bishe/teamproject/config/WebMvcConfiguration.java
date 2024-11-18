package com.bishe.teamproject.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").  //设置项目访问的虚拟映射路径虚拟路径/images
                //将C:/Users/ASUS/Pictures/images/路径下的所有资源文件全部映射到 /images/下
                        addResourceLocations("file:D:/毕设项目图片/");
    }
}
