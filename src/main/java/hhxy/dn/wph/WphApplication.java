package hhxy.dn.wph;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
/**
 * 启动类
 */
@SpringBootApplication
/**
 * 扫描Mapper文件
 */
@MapperScan("hhxy.dn.wph.mapper")
public class WphApplication {
    public static void main(String[] args) {
        SpringApplication.run(WphApplication.class, args);
    }
}
