package hhxy.dn.wph.config;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @Author: 邓宁
 * @Date: Created in 14:12 2019/5/4
 */
/**
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */
public class AlipayConfig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016093000634497";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCdfabTg/y952R56OP1RT/6vOPxVy/5EFprhvq4OoHmIm071P19lahQ9osITLTaRqdvvbKc1FrgmznhmoNy/hQ9pNx4Y1sW6mHIDVcd/DLN/jal1P+DQfG7oznQvZQNV+3WK6njFIrsVwFt6MZDQiMUOiW4gWl2F+vuobd1tCiUhdCKzytDHtEqvvKFebOrib43nZulFLZ0IC9WYNhnlgKvw/DamGDfSbRhqTqe5OoVVh1jjnNeJhlPTObs5dxR4MH/PQicnyPLQYz4mK2qFuqx8nKh0tk4rMvelHDuQJVcqBFp6ZZ4mmNXBG8Le/ZrHH60WzIdqUY/ZIOgm1e+gFIDAgMBAAECggEASdH+44AndOBEUXxfdbULAz2M3rAOdYE70NEJLleGUdfrObNhSn9e0OxvSOmjj/i0PQU+9pH3Mvavj9b8LGd6F1byXJ+9r6UXRk0ye4SeT74L4XlQdzbu1xPwYq6p+gMwIUaAMEeNwrAZpGoKnLYgeTe0PKK0Sz11Qnqc4tgaeVrvZaL4T0KITKxRrOBLmYybDDjGsnh5BIQwz05nDSo/fX2itQ4QjBpdQE9kq9H6UFRRE1xy4+by0y0LZMfMUXniOeZ3484s8b+vb0gGPCS0wBZIbWu5ZarvVLrYKyYSRPbc1aTr5jNpiaicv+UrB/B7fgQENnlg4NVsOpK8EYGtCQKBgQDTPiefAOanq9KMQeYhAEJCsauPCH8bmcZg7SZyPhP8wneqvwT5keQMaC2PGRW4x8CWLyCMulkjzLKFod7c3sO3zftC45+qLDZyZrVnYsaGU9SuGPW5zroaSymNHQJmd2o/4VNZicTkyF66ux4HeH+xuGrPEXzPuEVUq8y9rPe7FwKBgQC+2/sozbNC7aFV9jlkGif32oy636S8Eh4X4mFbDprCmhgct5/bdGhNpBdzQsBzBxOYdUKn4GVGS4tEN/Vc9XDlIFed0a55eu/cFbR12o16b87pLhFgPNokkvUtuEX/IqJQqrLGrZZrY6ttFzldu9b7e0OQDaKuOlVeNTWd0y8D9QKBgQDAEvXnx+PydXM+gPUE8L1O80nxBmK87/fHThr1Kaj23MP+hy2414SqG1fBk6eCmm5soHFSRGak/I0eoO6JmiwPxsU+LsaQ7x6aqvUrCk9E+JibrKpRxrzFB67CVqxGCzKa3wDwmypidCZeV6GYXWKOcAb+PQJlxUGVGZlSpiGjBQKBgHJEeCGczfeDF/XEXeXttwIwadM0NIeY/UVlds4ydzuNUGRZF68nCY96b8PFMj1r/jJn+glSUq/8aJvmONN36yhy7aICOMhobxTANfPdbpjgOevndeW9aQk+puXBjr7KuI09NZ23rfgLi0XcAq9416RmzzN+xmgzaorlpn0gQ+TFAoGAKOF8sSbnEQ0uzrN1o9GrM7jaMedZhlQ3XrIttrGRYrTo8jDxA6hW5AdaBjVumwqGiP3rcI8J1JYxcUg1lP0PE4G1iMoB3GESYdd/Xy5t158hli/F6dk/Av6h/5Xuvts3tXZhdkZMOWvKs/d48BBG5vM6AKxJ4b6t06+9mANJqZk=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsTKNjW3sdpoQBk6rpBUtopEmXZ1ZpPvXmXawgpq8ElGwAQzekH0omB48PHyuBOpzgIDijGKqcoswIBU89vjR0Msgypyn5D2UtPbr0gxXbdE1o0wNkuIiBHBzaiVaxwDjBzSs0ovycboQk0KqFOfoCQ31xkc/lw+6PQMp0nfowd7MPXHTbNAvwnE7T6+eYJlmmkHoqSs0Cd56TU6Ld6p9p9YxDw3+45vAdXvowszHHReypiX6bNJABP88ZftrCYz7zMZFShQM+hu77YALgoNC6Y5YCcvY9s2FReJgK7J5uoKjC7KxU5mKsSpP4hWB9gDkbagfM0rWenmHa+4YFh9lPwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://247y463w33.wicp.vip/wph/pay/notify";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://247y463w33.wicp.vip/wph/pay/return";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝日志
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
