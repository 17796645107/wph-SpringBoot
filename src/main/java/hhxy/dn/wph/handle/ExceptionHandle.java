package hhxy.dn.wph.handle;


import hhxy.dn.wph.entity.Result;
import hhxy.dn.wph.exception.GeneralException;
import hhxy.dn.wph.exception.GoodCartException;
import hhxy.dn.wph.exception.ProductException;
import hhxy.dn.wph.exception.UserException;
import hhxy.dn.wph.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 邓宁
 * @Date: Created in 22:59 2018/10/26
 * 异常捕获类
 */
@ControllerAdvice
public class ExceptionHandle {
    /**
     * @Description 日志
     */
    private static  final Logger logger= LoggerFactory.getLogger(ExceptionHandle.class);

    /**
     * 异常处理
     * @param e 异常信息
     * @return hhxy.dn.wph.entity.Result
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result handle(Exception e){
        //判断是否为自定义异常
        if(e instanceof UserException){
            UserException userException = (UserException) e;
            logger.error("【用户异常】 = {}",userException.getMessage());
            return ResultUtil.error(userException.getCode(),userException.getMessage());
        }else if(e instanceof ProductException){
            ProductException productException = (ProductException) e;
            logger.error("【商品异常】 = {}",productException.getMessage());
            return ResultUtil.error(productException.getCode(),productException.getMessage());
        }else if(e instanceof GoodCartException){
            GoodCartException goodCartException = (GoodCartException) e;
            logger.error("【购物车异常】 = {}",goodCartException.getMessage());
            return ResultUtil.error(goodCartException.getCode(),goodCartException.getMessage());
        }else if(e instanceof GeneralException){
            GeneralException generalException = (GeneralException) e;
            logger.error("【通用异常】 = {}",generalException.getMessage());
            return ResultUtil.error(generalException.getCode(),generalException.getMessage());
        }else{
            //否则就返回-1.未知错误
            logger.error("【未知异常】 = {}",e);
            return ResultUtil.error(-1,"未知错误");
        }
    }
}
