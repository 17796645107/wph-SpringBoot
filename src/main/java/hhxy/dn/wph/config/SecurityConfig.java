
package hhxy.dn.wph.config;

import hhxy.dn.wph.handle.SecurityAuthenticationFailureHandler;
import hhxy.dn.wph.handle.SecurityAuthenticationSuccessHandler;
import hhxy.dn.wph.handle.UserAccessDeniedHandle;
import hhxy.dn.wph.interceptor.UrlAuthenticationEntryPointInterceptor;
//import hhxy.dn.wph.interceptor.UrlInterceptor;
import hhxy.dn.wph.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: 邓宁
 * @Date: Created in 20:50 2018/11/24
 * Spring security 安全控制中心
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 身份验证
     */
    @Autowired
    private UrlAuthenticationEntryPointInterceptor urlAuthenticationEntryPoint;

    /**
     *  URL拦截器
     */
    /*@Autowired
    private UrlInterceptor urlInterceptor;*/

    /**
     *  登录认证类
     */
    @Autowired
    private UserAuthenticationProvider authenticationProvider;

    /**
     *  访问控制策略(授权)
     */
    @Autowired
    private UrlAccessDecisionManager urlAccessDecisionManager;

    /**
     *  用户登录权限异常捕获类
     */
    @Autowired
    private UserAccessDeniedHandle userAccessDeniedHandle;

    /**
     * 自定义成功处理器
     */
    @Autowired
    private SecurityAuthenticationSuccessHandler authenticationSuccessHandler;

    /**
     * 自定义失败处理器
     */
    @Autowired
    private SecurityAuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 所有人可访问的路径
     */
    String[] allowPath = {"/seller/*","/product/*"};

    /**
     * 定义认证用户信息获取来源，密码校验规则等
     * @param auth
     * @return void
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //自定义登录认证
        auth.authenticationProvider(authenticationProvider);
    }
    /**
     * 定义安全策略
     * @param http
     * @return void
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .anyRequest().permitAll()
                //没有权限返回的请求URL
                .and().exceptionHandling().accessDeniedPage("/security/accessDenied")
                .and().logout().permitAll()//定义logout不需要验证
                //防止跨站请求伪造
                .and().cors()
                .and().csrf().disable();
        /*//关闭csrf验证
        http.csrf().disable()
                //token验证
                .httpBasic().authenticationEntryPoint(urlAuthenticationEntryPoint)
                //所有人都可以访问
                .and()
                .authorizeRequests()
                .antMatchers(allowPath).permitAll()
                //自定义验证
                .and()
                .authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <Obj extends FilterSecurityInterceptor> Obj postProcess(Obj obj) {
                        obj.setSecurityMetadataSource(urlInterceptor);
                        obj.setAccessDecisionManager(urlAccessDecisionManager);
                        return obj;
                    }
                })
                .and()
                .formLogin()
                //自定义登录路径
                .loginProcessingUrl("/user/login")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()
                .logout()
                .logoutUrl("/user/logout")
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(userAccessDeniedHandle);*/
    }

    @Override
    public void configure(WebSecurity web) throws Exception { }
}
