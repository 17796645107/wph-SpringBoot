package hhxy.dn.wph.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import hhxy.dn.wph.config.AlipayConfig;
import hhxy.dn.wph.entity.Order;
import hhxy.dn.wph.entity.Result;
import hhxy.dn.wph.enums.OrderExceptionEnum;
import hhxy.dn.wph.exception.OrderException;
import hhxy.dn.wph.service.OrderService;
import hhxy.dn.wph.service.PayService;
import hhxy.dn.wph.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author 邓宁
 * @date Created in 14:05 2019/5/4
 */
@RequestMapping("/pay")
@RestController
public class PayController {

    @Autowired
    private PayService payService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/payment")
    public Result pay(
                    @RequestParam("WIDout_trade_no") String outTradeNo,
                    @RequestParam("WIDtotal_amount")String totalAmount,
                    @RequestParam("WIDsubject")String subject,
                    @RequestParam("WIDbody")String body){
        String result = payService.pay(outTradeNo,totalAmount,subject,body);
        return ResultUtil.success(result);
    }

    /**
     * :异步回调: 第三方支付接口发一个后台通知给商户平台，一般场景用户修改订单信息。
     * @param [request, response]
     * @return Result
     */
    @RequestMapping("/notify")
    public Result alipayNotifyNotice(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            try {
                valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            params.put(name, valueStr);
        }
        //调用SDK验证签名
        boolean signVerified = false;
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        /* 实际验证过程建议商户务必添加以下校验：
        1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
        2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
        3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
        4、验证app_id是否为该商户本身。
        */
        //验证成功
        if(signVerified) {
            //商户订单号
            String outTradeNo = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String tradeNo = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //交易状态
            String tradeStatus = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

            if("TRADE_FINISHED".equals(tradeStatus)){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序
                throw new OrderException(OrderExceptionEnum.ORDER_EXCEPTION_ENUM);
                //注意：
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            }else if ("TRADE_SUCCESS".equals(tradeStatus)){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序
                Order order = orderService.getOrderByOrderId(outTradeNo);
                if (order == null){
                    throw new OrderException(OrderExceptionEnum.ORDER_EXCEPTION_ENUM);
                }
                int result = orderService.updateOrderSetPayNo(outTradeNo,tradeNo);
                if (result != 1){
                    throw new OrderException(OrderExceptionEnum.ORDER_UPDATE_STATE_ERROR);
                }
                //注意：
                //付款完成后，支付宝系统发送该交易状态通知
            }
            return ResultUtil.success();

        }else {//验证失败
            return ResultUtil.error(-2,"验证失败");
        }
        //验证失败
        /*if(!signVerified) {
            throw new OrderException(OrderExceptionEnum.orderValidt_error);
        }
        //商户订单号
        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
        //支付宝交易号
        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
        //交易状态
        String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
        if(trade_status.equals("TRADE_FINISHED")){
            //判断该笔订单是否在商户网站中已经做过处理
            //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
            //如果有做过处理，不执行商户的业务程序

            //注意：
            //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            throw new OrderException(OrderExceptionEnum.orderValidt_error);
        }else if (trade_status.equals("TRADE_SUCCESS")){
            //判断该笔订单是否在商户网站中已经做过处理
            //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
            //如果有做过处理，不执行商户的业务程序

            //注意：
            //付款完成后，支付宝系统发送该交易状态通知
            //查询订单
            Order order = orderService.getOrderByOrderId(out_trade_no);
            if (order == null){
                throw new OrderException(OrderExceptionEnum.orderValidt_error);
            }
            int result = orderService.updateOrderPayNo(out_trade_no,trade_no);
            if (result != 1){
                throw new OrderException(OrderExceptionEnum.ORDER_UPDATE_STATE_ERROR);
            }
        }
        return ResultUtil.success();*/
    }

    /**
     * :同步回调: 整个支付流程完毕，使用同步方式将参数重定向给商户平台，一般场景用于展示结果。
     * @param [request, response]
     * @return Result
     */
    @RequestMapping("/return")
    public Result alipayReturnNotice(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        //调用SDK验证签名
        boolean signVerified = false;
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
//        请求参数
        /*charset	utf-8
        out_trade_no	201905240015221558628122014
        method	alipay.trade.page.pay.return
                total_amount	1000.00
        sign	MWxowGai3PBppZTE6htFuJip6ewa3Y4d4AcCAc5lkWMMfx8+82h8443R/u2voWvQtpSnNhqrigJBOCjTPEnKJA+9zNKsIQRnejEhfHMIiUNEYnR9yK9zno6e53lL1hFGzIMuJGno4exukmula2yHtHlCLfGvG7tfN1boGQEY6rAwBI9najv5RqEKALt5LAP1DroHghJhgEzd8BttiMj9b8odRUVXVqC0PHpH6jttoCf2rQ4h/Y1seOMWto6pu2XgbQGcNTgYH9b/nirE9dvyZPcXMywmIvFi9HBknHg6YqeN8ZHrfvc52RHldFgDJKWrOMCz2TjL5ZiId0Pm3jRbQg==
                trade_no	2019052422001410481000045613
        auth_app_id	2016093000634497
        version	1.0
        app_id	2016093000634497
        sign_type	RSA2
        seller_id	2088102178009336
        timestamp	2019-05-24+00:16:16*/
        //——请在这里编写您的程序（以下代码仅作参考）——
        if(signVerified) {
            //商户订单号
            String outTradeNo = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
            //支付宝交易号
            String tradeNo = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
            //付款金额
            String totalAmount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

            Order order = new Order();
            order.setOrderNo(outTradeNo);
            order.setPayNo(tradeNo);
            order.setProductTotal(Double.valueOf(totalAmount));
            return ResultUtil.success(order);
        }else {
            return ResultUtil.error(-2,"验签失败");
        }
    }
}
