package hhxy.dn.wph.config;

import hhxy.dn.wph.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 邓宁
 * @date Created in 15:21 2018/11/23
 * //拦截器
 *
 * */

//@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    /**
     * 不拦截的路径
     */
    private String[]excludePath={"/Resource/**","/wph/**","/view/user/user_login.html","/view/index/index.html"};

    /** :重写添加拦截器方法，添加登录拦截器
     * @param [registry]
     * @return void
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截哪些路径("/**"代表拦截所有路径)
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**.html")
                //不拦截哪些路径
                .excludePathPatterns(excludePath);
    }
}
