package hhxy.dn.wph.acpect;

import hhxy.dn.wph.exception.UserException;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: 邓宁
 * @Date: Created in 0:21 2019/6/11
 * 切面类
 * 用户模块控制器参数验证
 */
@Aspect
@Component
public class ParamValidatorAspect {

    /**
     * 切点
     * @param
     * @return void
     */
    @Pointcut("execution(public * hhxy.dn.wph.controller.UserController.*(..))")
    public void validator(){ }

    /**
     * 异常通知
     * @param e
     * @return void
     */
    @AfterThrowing(pointcut = "validator()",throwing = "e")
    public void afterThrowing(Exception e) throws Throwable {
        //捕获参数验证异常
        if(e instanceof IllegalArgumentException){
            throw new UserException(e.getMessage(),1999);
        }
    }
}
