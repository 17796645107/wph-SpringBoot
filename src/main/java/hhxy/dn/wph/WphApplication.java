package hhxy.dn.wph;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * 启动类
 */
@SpringBootApplication
@MapperScan("hhxy.dn.wph.mapper")
public class WphApplication {
    public static void main(String[] args) {
        SpringApplication.run(WphApplication.class, args);
    }
}
