
package hhxy.dn.wph.config;

import hhxy.dn.wph.handle.UserAccessDeniedHandle;
import hhxy.dn.wph.interceptor.UserLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
/**
 * @Author: 邓宁
 * @Date: Created in 20:50 2018/11/24
 */

//安全控制中心
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /*@Autowired
    UserDetailsServiceImpl userDetailsService;//用户业务接口
    @Autowired
    UserLoginInterceptor userLoginInterceptor;//UrlFilterI
    @Autowired
    UrlAccessDecisionManager urlAccessDecisionManager;//UrlManager
    @Autowired
    UserAccessDeniedHandle userAccessDeniedHandle;//用户登录权限异常捕获类*/

    //用户登录不拦截的路径
    private String[] excludePath = {"/user/**","/product/**"};

    /*定义认证用户信息获取来源，密码校验规则等*/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //注入userDetailsService，需要实现userDetailsService接口
        //auth.userDetailsService(userDetailsService);
        /*auth.inMemoryAuthentication()
                .withUser("username").password("123456").roles("USER").and()
                .withUser("admin").password("123456").roles("User","ADMIN");*/
    }

    /*定义安全策略*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()//配置安全策略
                .anyRequest().permitAll()
                .and().exceptionHandling().accessDeniedPage("/security/accessDenied")//没有权限返回的请求URL
                .and().logout().permitAll()//定义logout不需要验证
                //防止跨站请求伪造
                .and().cors()
                .and().csrf().disable();
                //.antMatchers(excludePath).permitAll()//定义请求不需要验证
                //.anyRequest().authenticated()//其余的所有请求都需要验证

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/global/**","/static/**");
    }
}
