package hhxy.dn.wph.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: 邓宁
 * @Date: Created in 22:03 2018/12/8
 */
//处理Spring Security拦截跨域请求问题而写的类
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") .allowedMethods("*")
                .allowedHeaders("*") .allowCredentials(true)
                .maxAge(3600);

    }
}
