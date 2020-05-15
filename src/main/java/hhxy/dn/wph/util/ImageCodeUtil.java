package hhxy.dn.wph.util;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

//图片验证码工具类
public class ImageCodeUtil {
    //随机字符字典
    private static final char[] CHARS = {'2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
            'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    private static final int number = 4; //验证码长度
    private static final int width = 100;//图片宽度
    private static final int height = 30;//图片高度
    private static Random random = new Random();

    //生成随机数
    private static String getRandomString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < number; i++) {
            builder.append(CHARS[random.nextInt(CHARS.length)]);//生成随机数,从字典中选取字符
        }
        return builder.toString();
    }

    //生成随机颜色
    private static Color getRandomColor() {
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

    //返回颜色的反色
    private static Color getReverseColor(Color c) {
        return new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue());
    }

    public static void getImageCode(HttpServletResponse response, HttpSession session) throws IOException {
        //设置response头信息
        //禁止缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        /* 随机字符串 */
        String randomString = ImageCodeUtil.getRandomString();
        //放入的session中
        session.setAttribute("VALIDATE_CODE", randomString);
        //随机颜色
        Color color = ImageCodeUtil.getRandomColor();
        //反色
        Color reverseColor = ImageCodeUtil.getReverseColor(color);
        //创建一个彩色图片
        BufferedImage img = new BufferedImage(ImageCodeUtil.width, ImageCodeUtil.height, BufferedImage.TYPE_INT_BGR);
        //获取绘图对象
        Graphics2D g = img.createGraphics();
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        g.setColor(color);
        g.fillRect(0, 0, ImageCodeUtil.width, ImageCodeUtil.height);
        g.setColor(reverseColor);
        g.drawString(randomString, 18, 20);
        g.dispose();
        ImageIO.write(img, "JPEG", response.getOutputStream());
        response.getOutputStream().flush();
    }
}
