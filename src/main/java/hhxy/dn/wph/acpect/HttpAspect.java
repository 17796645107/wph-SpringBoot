package hhxy.dn.wph.acpect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户模块方法调用记录切面类
 * @author 邓宁
 * @date Created in 11:41 2018/10/25
 */
@Aspect
@Component
public class HttpAspect {
    private static final Logger logger= LoggerFactory.getLogger(HttpAspect.class);

    /**
     * 切点
     */
    @Pointcut("execution(public * hhxy.dn.wph.controller.UserController.*(..))")
    public static void log(){ }

    /**
     * 在方法之前调用
     * @param joinPoint joinPoint
     */
    @Before("log()")
    public void logBefore(JoinPoint joinPoint){
        //获取ServletRequest
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //获取HttpServletRequest
        HttpServletRequest request=attributes.getRequest();

        if (logger.isInfoEnabled()){
            //url
            logger.info("url = {}",request.getRequestURL());
            //action
            logger.info("action = {}",request.getMethod());
            //ip
            logger.info("ip = {}",request.getRemoteAddr());
            //method
            logger.info("method = {}",joinPoint.getSignature().getDeclaringTypeName() +"."+
                    joinPoint.getSignature().getName());
            //params
            logger.info("params = {}",joinPoint.getArgs());
        }

    }

    /**
     * 在方法之后调用
     */
    @After("log()")
    public void logAfter(JoinPoint joinPoint){
        if (logger.isInfoEnabled()){
            logger.info(joinPoint.getSignature().getName()+"结束");
        }
    }

    /**
     * 获取返回信息
     * @param object 切面
     */
    @AfterReturning(returning = "object",pointcut = "log()")
    public void logAfterReturning(Object object){
        if (logger.isInfoEnabled()){
            logger.info("response = {}",object);
        }
    }
}
