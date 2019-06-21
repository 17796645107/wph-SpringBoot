
package hhxy.dn.wph.config;

import hhxy.dn.wph.handle.UserAccessDeniedHandle;
import hhxy.dn.wph.interceptor.UserLoginInterceptor;
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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: 邓宁
 * @Date: Created in 20:50 2018/11/24
 * Spring security 安全控制中心
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /*@Autowired
    UserServiceImpl userDetailsService;//用户业务接口
    @Autowired
    UserLoginInterceptor userLoginInterceptor;//UrlFilterI
    @Autowired
    UrlAccessDecisionManager urlAccessDecisionManager;//UrlManager
    @Autowired
    UserAccessDeniedHandle userAccessDeniedHandle;//用户登录权限异常捕获类
*/

    /**
     * 定义认证用户信息获取来源，密码校验规则等
     * @param auth
     * @return void
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //注入userDetailsService，需要实现userDetailsService接口
//        auth.userDetailsService(userDetailsService);
    }

    /**
     * 定义安全策略
     * @param http
     * @return void
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //配置安全策略
        http
            .authorizeRequests()
                .anyRequest().permitAll()
                //没有权限返回的请求URL
                .and().exceptionHandling().accessDeniedPage("/security/accessDenied")
                .and().logout().permitAll()//定义logout不需要验证
                //防止跨站请求伪造
                .and().cors()
                .and().csrf().disable();
        /*http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <Obj extends FilterSecurityInterceptor> Obj postProcess(Obj obj) {
                        obj.setSecurityMetadataSource(userLoginInterceptor);
                        obj.setAccessDecisionManager(urlAccessDecisionManager);
                        return obj;
                    }
                })
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        PrintWriter out = httpServletResponse.getWriter();
                        StringBuffer sb = new StringBuffer();
                        sb.append("{\"status\":\"error\",\"msg\":\"");
                        if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
                            sb.append("用户名或密码输入错误，登录失败!");
                        } else if (e instanceof DisabledException) {
                            sb.append("账户被禁用，登录失败，请联系管理员!");
                        } else {
                            sb.append("登录失败!");
                        }
                        sb.append("\"}");
                        out.write(sb.toString());
                        out.flush();
                        out.close();
                    }
                })
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        PrintWriter out = httpServletResponse.getWriter();
                        String s = "{\"status\":\"success\",\"msg\":"  + "}";
                        out.write(s);
                        out.flush();
                        out.close();
                    }
                })
                .and()
                .logout()
                .permitAll()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .accessDeniedHandler(userAccessDeniedHandle);*/

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
//        web.ignoring()
//                .antMatchers("/global/**","/static/**");
    }
}
