package hhxy.dn.wph.interceptor; /**
 * @Author 邓宁
 * @Date Created in 11:00 2019/7/17
 * @Description
 */

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *   @Author: 邓宁
 *   @Date: Created in 11:00 2019/7/17
 *   @Describe: 身份验证
 */
@Component
public class UrlAuthenticationEntryPointInterceptor implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

    }
}
