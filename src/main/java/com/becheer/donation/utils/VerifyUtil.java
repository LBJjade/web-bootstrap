package com.becheer.donation.utils;

/*
* VerifyUtil 验证码生成类
* Creator : xiaokepu
* Date : 2017-09-15
*/

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class VerifyUtil {

    private BufferedImage image;// 图像
    private String str;// 验证码
    private static char code[] = "abcdefghijkmnopqrstuvwxyzABCDEFGHIJKMNPQRSTUVWXYZ123456789".toCharArray();

    private VerifyUtil() {
        init();// 初始化属性
    }

    /*
     * 取得RandomNumUtil实例
     */
    public static VerifyUtil Instance() {
        return new VerifyUtil();
    }

    /*
     * 取得验证码图片
     */
    public BufferedImage getImage() {
        return this.image;
    }

    /*
     * 取得图片的验证码
     */
    public String getString() {
        return this.str;
    }

    private void init() {
        // 在内存中创建图象
        int width = 90, height = 30;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文
        Graphics graphics = bufferedImage.getGraphics();
        // 生成随机类
        Random random = new Random();
        // 设定背景色
        graphics.setColor(getRandColor(200, 250));
        graphics.fillRect(0, 0, width, height);
        // 设定字体
        graphics.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        // 干扰线
        graphics.setColor(getRandColor(160, 200));
        for (int i = 0; i < 200; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            graphics.drawLine(x, y, x + xl, y + yl);
        }
        // 取随机产生的认证码(4位数字)
        String strRandom = "";
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(code[random.nextInt(code.length)]);
            strRandom += rand;
            // 将认证码显示到图象中
            graphics.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            // 绘制
            graphics.drawString(rand, 20 * i + 10, 20);
        }
        // 赋值验证码
        this.str = strRandom;

        // 图象生效
        graphics.dispose();
        this.image = bufferedImage;
    }

    /*
     * 给定范围获得随机颜色
     */
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
