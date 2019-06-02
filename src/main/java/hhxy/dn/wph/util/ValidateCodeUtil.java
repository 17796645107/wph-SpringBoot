package hhxy.dn.wph.util;

import java.awt.Color;
import java.util.Random;
//图片验证码工具类
public class ValidateCodeUtil {
	//随机字符字典
	public static final char[]CHARS={'2','3','4','5','6','7','8','9','a','b','c','d',
		'e','f','g','h','j','k','m','n','p','q','r','s','t','u','v','w','x','y','z'};
	
	public static final int number=4; //验证码长度
	public static final int width=100;//图片宽度
	public static final int height=30;//图片高度
	static Random random=new Random();
	//生成随机数
	public static String getRandomString(){
		StringBuffer buffer=new StringBuffer();
		for(int i=0;i<number;i++){
			buffer.append(CHARS[random.nextInt(CHARS.length)]);//生成随机数,从字典中选取字符 
		}
		return buffer.toString(); 
	}
	//生成随机颜色
	public static Color getRandomColor(){
		return new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
	}
	//返回颜色的反色
	public static Color getReverseColor(Color c){
		return new Color(255-c.getRed(),255-c.getGreen(),255-c.getBlue()); 
	}

	//测试方法
	public static void main(String[] args) {
		System.out.println(ValidateCodeUtil.getRandomString());
	}
}
