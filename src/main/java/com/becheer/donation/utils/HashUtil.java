package com.becheer.donation.utils;

/*
* HashUtil HASH工具
* Creator : xiaokepu
* Date : 2017-09-14
*/


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {

    //获取密码
    public static String GetPassword(String pwd){
        if (pwd==null||pwd.length()==0){
            return pwd;
        }
        pwd=MD5(pwd);
        pwd+="becheer";
        StringBuffer stringBuffer=new StringBuffer(pwd);
        stringBuffer.reverse().toString();
        return SHA256(pwd);
    }

    //加密文件名 OSS用
    public static String getEncryptedFileName(String fileName){
        if (fileName==null||fileName.length()==0){
            return fileName;
        }
        fileName=SHA256(fileName);
        fileName="B"+fileName+"bc";
        fileName=MD5(fileName);
        return fileName;
    }

    //MD5
    public static String MD5(String source) {
        return SHA(source,"MD5");
    }

    //SHA1
    public static String  SHA1(String source){
        return SHA(source,"SHA-1");
    }

    //SHA256
    public static String SHA256(String source){
        return SHA(source,"SHA-256");
    }

    //HASH
    private static String SHA(String source, String type)
    {
        // 返回值
        String strResult = null;

        // 是否是有效字符串
        if (source != null && source.length() > 0)
        {
            try
            {
                // SHA 加密开始
                // 创建加密对象
                MessageDigest messageDigest = MessageDigest.getInstance(type);
                // 传入要加密的字符串
                messageDigest.update(source.getBytes());
                // 得到 byte 类型
                byte byteBuffer[] = messageDigest.digest();

                // byte转string
                StringBuffer strHexString = new StringBuffer();
                // 遍历
                for (int i = 0; i < byteBuffer.length; i++)
                {
                    String hex = Integer.toHexString(0xff & byteBuffer[i]);
                    if (hex.length() == 1)
                    {
                        strHexString.append('0');
                    }
                    strHexString.append(hex);
                }
                // 得到返回結果
                strResult = strHexString.toString();
            }
            catch (NoSuchAlgorithmException e)
            {
                e.printStackTrace();
            }
        }

        return strResult;
    }
}

