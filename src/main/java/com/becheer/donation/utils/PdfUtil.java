package com.becheer.donation.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 包名: com.becheer.donation.utils
 * 文件说明: pdf工具类
 * 创建人:amber
 * 创建日期: 2017/12/7 17:10
 * 版本:V1.0
 * Copyright © 2017 广州品清科技有限公司
 */
public class PdfUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PdfUtil.class);

    /**
     * html导出为pdf
     * @param httpUrl html http 地址
     * @param filePath 保存的物理路径
     * @param dpi pdf dpi，实测400左右效果比较好
     * @return
     */
    public static boolean htmlToPdf(String httpUrl,String filePath,int dpi){
        boolean result= false;
        try{
            String strMessage=null;
            String strTerminal="wkhtmltopdf "+ "-d "+dpi+" "+httpUrl+" "+filePath;
            Process process = Runtime.getRuntime().exec (strTerminal);
            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            //读取控制台输出
            while ((strMessage = stdError.readLine()) != null) {
                //导出成功
                if (strMessage.trim().equals("Done")){
                    result=true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return result;
    }

}
