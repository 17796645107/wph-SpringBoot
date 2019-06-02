package hhxy.dn.wph;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @Author: 邓宁
 * @Date: Created in 15:21 2019/5/12
 */

//部署到Tomcat的启动类
public class WphServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WphApplication.class);
    }
}
