package top.zdhunter.driverFriend.common.utils;
import top.zdhunter.driverFriend.bean.result.VerifyCode;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class VerifyCodeImageUtil {
    private static final String randKey = "Verify-Code-Key";//放到session中的key
    private final static String randString = "0123456789ABCDEFGHIJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz";//随机产生数字与字母组合的字符串
    private final static int width = 95;    // 图片宽
    private final static int height = 25;   // 图片高
    private final static int lineSize = 40; // 干扰线数量
    private final static int stringNum = 4; // 随机产生字符数量
    private static Random random = new Random();

    /**
     * 获得字体
     */
    private static Font getFont() {
        return new Font("Fixedsys", Font.BOLD, 18);
    }

    /**
     * 获得颜色
     */
    private static Color getRandColor(int fc, int bc) {
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }

    /**
     * 输出随机图片
     */
    public static VerifyCode outputVerifycodeImage(HttpServletResponse response) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics(); // 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        g.fillRect(0, 0, width, height); //图片大小
        g.setFont(new Font("Times New Roman", Font.PLAIN, 18)); //字体大小
        g.setColor(getRandColor(110, 133)); //字体颜色

        // 绘制干扰线
        for (int i = 0; i <= lineSize; i++) {
            drowLine(g);
        }
        // 绘制随机字符
        String randomString = "";
        for (int i = 1; i <= stringNum; i++) {
            randomString = drawString(g, randomString, i);
        }
        g.dispose();

        try {
            // 将内存中的图片通过流动形式输出到客户端
            String key = UuidUtil.randomUUID();
            response.setContentType("image/jpeg"); //设置响应类型，告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache"); //设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader(randKey, key);
            response.setDateHeader("Expire", 0);
            ImageIO.write(image, "JPEG", response.getOutputStream());
            return new VerifyCode(key, randomString);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 绘制字符串
     */
    private static String drawString(Graphics g, String randomString, int i) {
        g.setFont(getFont());
        g.setColor(new Color(random.nextInt(101), random.nextInt(111), random
                .nextInt(121)));
        String rand = getRandomString(random.nextInt(randString
                .length()));
        randomString += rand;
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(rand, 13 * i, 16);
        return randomString;
    }

    /**
     * 绘制干扰线
     */
    private static void drowLine(Graphics g) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x + xl, y + yl);
    }

    /**
     * 获取随机的字符
     */
    private static String getRandomString(int num) {
        return String.valueOf(randString.charAt(num));
    }

}
