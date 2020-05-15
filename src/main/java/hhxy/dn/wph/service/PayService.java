package hhxy.dn.wph.service;

/**
 * @author 邓宁
 * @date Created in 14:07 2019/5/4
 */

public interface PayService {
    String pay(String out_trade_no, String total_amount, String subject, String body);
}
