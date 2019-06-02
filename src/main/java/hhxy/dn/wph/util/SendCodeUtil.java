package hhxy.dn.wph.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import java.util.Random;

public class SendCodeUtil {

    //生成6位数随机验证码
    public static String getRandomCode(){
        Random random = new Random();
        Integer code =  random.nextInt(999999);
        return String.valueOf(code < 100000 ? code += 100000 : code);
    }

	 public static SendSmsResponse sendSms(String phoneNumber,String code) throws ClientException {
	        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");//不必修改  
	        System.setProperty("sun.net.client.defaultReadTimeout", "10000");//不必修改  
	        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIcU1hnXvDeE3y", "VO2ztlGUKhLZVwvgp1q6K0kQpjKPrA");//填写自己的AccessKey ID和Secret  
	        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", "dysmsapi.aliyuncs.com");//不必修改  
	        IAcsClient acsClient = new DefaultAcsClient(profile);//不必修改  
	        SendSmsRequest request = new SendSmsRequest();//不必修改  
	        request.setPhoneNumbers(phoneNumber);//手机号码
	        request.setSignName("邓宁宁 ");//此处填写已申请的短信签名  
	        request.setTemplateCode("SMS_140805067");//此处填写获得的短信模版CODE  
	        request.setTemplateParam("{\"code\":\""+code+"\"}");//因为我起的变量名为${code}, 因此此处对应填写验证码   
	        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);//不必修改  
	        return sendSmsResponse;  
	    }
    //测试方法
    public static void main(String[] args) throws ClientException{
        /*SendSmsResponse response = sendSms("18790842691",7758521);
        if(response.getCode() != null && response.getCode().equals("OK")) {
            //请求成功
            System.out.println("ok");
        }else{
            //未成功的错误信息
            System.out.println(response.getMessage());
        }*/
        for (int i = 0; i <= 100 ;i++){
            System.out.println(SendCodeUtil.getRandomCode());
        }
//        System.out.println(SendCodeUtil.getRandomCode());
    }
}
