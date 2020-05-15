package hhxy.dn.wph;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author 邓宁
 * @date Created in 15:21 2019/5/12
 */

//部署到Tomcat的启动类
public class WphServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WphApplication.class);
    }
}
