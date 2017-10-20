package com.becheer.core.util;

import net.coobird.thumbnailator.Thumbnails;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 图片处理辅助类
 *
 * @author ShenHuaJie
 * @since 2012-03-21
 */
public final class ImageUtil {
    private ImageUtil() {
    }

    public static void main(String[] args) throws Exception {
        String text = "http://www.baidu.com";
        System.out.println(encodeBufferedImageToBase64(QRCodeUtil.createQRCode(text, 300, 300), "png"));
    }
    /**
     * 将网络图片进行Base64位编码
     *
     * @param imageUrl 图片的url路径，如http://.....xx.jpg
     * @return
     */
    public static String encodeImageToBase64(URL imageUrl) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        ByteArrayOutputStream outputStream = null;
        try {
            BufferedImage bufferedImage = ImageIO.read(imageUrl);
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", outputStream);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(outputStream.toByteArray());// 返回Base64编码过的字节数组字符串
    }

    public static String encodeBufferedImageToBase64(BufferedImage bufferedImage, String formatName) {
        ByteArrayOutputStream outputStream = null;
        try {
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, formatName, outputStream);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return "data:image/" + formatName + ";base64, " + encoder.encode(outputStream.toByteArray());// 返回Base64编码过的字节数组字符串
    }

    /**
     * * 转换图片大小，不变形
     *
     * @param img    图片文件
     * @param width  图片宽
     * @param height 图片高
     */
    public static final void changeImge(File img, int width, int height) {
        try {
            Thumbnails.of(img).size(width, height).keepAspectRatio(false).toFile(img);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("图片转换出错！", e);
        }
    }

    /**
     * 根据比例缩放图片
     *
     * @param orgImg     源图片BufferedImage
     * @param scale      比例
     * @param targetFile 缩放后的图片存放路径
     * @throws IOException
     */
    public static final void scale(BufferedImage orgImg, double scale, String targetFile) throws IOException {
        Thumbnails.of(orgImg).scale(scale).toFile(targetFile);
    }

    public static final void scale(String orgImgFile, double scale, String targetFile) throws IOException {
        Thumbnails.of(orgImgFile).scale(scale).toFile(targetFile);
    }

    /**
     * 图片格式转换
     *
     * @param orgImgFile
     * @param width
     * @param height
     * @param suffixName
     * @param targetFile
     * @throws IOException
     */
    public static final void format(String orgImgFile, int width, int height, String suffixName, String targetFile)
            throws IOException {
        Thumbnails.of(orgImgFile).size(width, height).outputFormat(suffixName).toFile(targetFile);
    }

    /**
     * 根据宽度同比缩放
     *
     * @param orgImg      源图片
     * @param targetWidth 缩放后的宽度
     * @param targetFile  缩放后的图片存放路径
     * @throws IOException
     */
    public static final double scaleWidth(BufferedImage orgImg, int targetWidth, String targetFile) throws IOException {
        int orgWidth = orgImg.getWidth();
        // 计算宽度的缩放比例
        double scale = targetWidth * 1.00 / orgWidth;
        // 裁剪
        scale(orgImg, scale, targetFile);

        return scale;
    }

    public static final void scaleWidth(String orgImgFile, int targetWidth, String targetFile) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File(orgImgFile));
        scaleWidth(bufferedImage, targetWidth, targetFile);
    }

    /**
     * 根据高度同比缩放
     *
     * @param orgImg       //源图片
     * @param targetHeight //缩放后的高度
     * @param targetFile   //缩放后的图片存放地址
     * @throws IOException
     */
    public static final double scaleHeight(BufferedImage orgImg, int targetHeight, String targetFile) throws IOException {
        int orgHeight = orgImg.getHeight();
        double scale = targetHeight * 1.00 / orgHeight;
        scale(orgImg, scale, targetFile);
        return scale;
    }

    public static final void scaleHeight(String orgImgFile, int targetHeight, String targetFile) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File(orgImgFile));
        // int height = bufferedImage.getHeight();
        scaleHeight(bufferedImage, targetHeight, targetFile);
    }

    // 原始比例缩放
    public static final void scaleWidth(File file, Integer width) throws IOException {
        String fileName = file.getName();
        String filePath = file.getAbsolutePath();
        String postFix = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        // 缩放
        BufferedImage bufferedImg = ImageIO.read(file);
        String targetFile = filePath + "_s" + postFix;
        scaleWidth(bufferedImg, width, targetFile);
        String targetFile2 = filePath + "@" + width;
        new File(targetFile).renameTo(new File(targetFile2));
    }
}
