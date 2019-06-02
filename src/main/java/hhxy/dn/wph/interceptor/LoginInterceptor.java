package hhxy.dn.wph.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import hhxy.dn.wph.entity.User;
import hhxy.dn.wph.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @Author: 邓宁
 * @Date: Created in 15:02 2018/11/23
 */
//用户登录拦截器
//@Component
public class LoginInterceptor implements HandlerInterceptor {

    //日志记录
    private static final Logger LOGGER= LoggerFactory.getLogger(LoggerFactory.class);

    //拦截实现
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.debug(request.getRequestURL().toString());
        //从Session中获取USER对象
        User user= (User) request.getSession().getAttribute("USER");
        //用户已登录
        if (user!=null){
            return true;
        }
        //用户未登录
        else {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter out=response.getWriter();
            ObjectMapper mapper=new ObjectMapper();
            //返回信息json
            String result=mapper.writeValueAsString(ResultUtil.error(-1,"请登录"));
            LOGGER.debug(result);
//            out.print(result);
            //重定向到登录页面
            response.sendRedirect("/wph/view/user/user_login.html");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
