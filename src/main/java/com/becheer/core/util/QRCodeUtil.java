package com.becheer.core.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.UUID;

/**
 * 二维码工具类
 * 
 */

public class QRCodeUtil {
	public static BufferedImage createQRCode(String text, Integer width, Integer height) {
		try {
			HashMap<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);

			return MatrixToImageWriter.toBufferedImage(bitMatrix);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String createQrcode(String dir, String _text) {
		String qrcodeFilePath = "";
		try {
			int qrcodeWidth = 300;
			int qrcodeHeight = 300;
			String qrcodeFormat = "png";
			HashMap<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			BitMatrix bitMatrix = new MultiFormatWriter().encode(_text, BarcodeFormat.QR_CODE, qrcodeWidth,
					qrcodeHeight, hints);

			BufferedImage image = new BufferedImage(qrcodeWidth, qrcodeHeight, BufferedImage.TYPE_INT_RGB);
			File qrcodeFile = new File(dir + "/" + UUID.randomUUID().toString() + "." + qrcodeFormat);
			ImageIO.write(image, qrcodeFormat, qrcodeFile);
			MatrixToImageWriter.writeToPath(bitMatrix, qrcodeFormat, qrcodeFile.toPath());
			qrcodeFilePath = qrcodeFile.getAbsolutePath();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return qrcodeFilePath;
	}

	public static String decodeQr(String filePath) {
		String retStr = "";
		if ("".equalsIgnoreCase(filePath) && filePath.length() == 0) {
			return "图片路径为空!";
		}
		try {
			BufferedImage bufferedImage = ImageIO.read(new FileInputStream(filePath));
			LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
			Binarizer binarizer = new HybridBinarizer(source);
			BinaryBitmap bitmap = new BinaryBitmap(binarizer);
			HashMap<DecodeHintType, Object> hintTypeObjectHashMap = new HashMap<>();
			hintTypeObjectHashMap.put(DecodeHintType.CHARACTER_SET, "UTF-8");
			Result result = new MultiFormatReader().decode(bitmap, hintTypeObjectHashMap);
			retStr = result.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retStr;
	}
}
